package model;

import java.io.*;
import java.util.*;

public class GerenciadorTimes {
    private Map<String, Set<Jogador>> times;
    private static final String ARQUIVO_DADOS = "times_dados.txt";

    public GerenciadorTimes() {
        times = new HashMap<>();
        carregarDados();
    }

    public boolean adicionarTime(String nomeTime) {
        if (nomeTime == null || nomeTime.trim().isEmpty()) {
            throw new IllegalArgumentException("\nNome do time não pode ser vazio");
        }
        
        String timeNormalizado = nomeTime.trim();
        if (times.containsKey(timeNormalizado)) {
            return false; // Time já existe
        }
        
        times.put(timeNormalizado, new TreeSet<>());
        salvarDados();
        return true;
    }

    public boolean adicionarJogador(String nomeTime, Jogador jogador) {
        if (nomeTime == null || nomeTime.trim().isEmpty()) {
            throw new IllegalArgumentException("\nNome do time não pode ser vazio");
        }
        if (jogador == null) {
            throw new IllegalArgumentException("\nJogador não pode ser nulo");
        }

        String timeNormalizado = nomeTime.trim();
        times.putIfAbsent(timeNormalizado, new TreeSet<>());
        
        Set<Jogador> jogadores = times.get(timeNormalizado);
        if (jogadores.contains(jogador)) {
            return false; // Jogador já existe no time
        }
        
        boolean adicionado = jogadores.add(jogador);
        if (adicionado) {
            salvarDados();
        }
        return adicionado;
    }

    public boolean removerTime(String nomeTime) {
        if (nomeTime == null || nomeTime.trim().isEmpty()) {
            return false;
        }
        
        boolean removido = times.remove(nomeTime.trim()) != null;
        if (removido) {
            salvarDados();
        }
        return removido;
    }

    public boolean removerJogador(String nomeTime, String nomeJogador) {
        if (nomeTime == null || nomeJogador == null) {
            return false;
        }

        Set<Jogador> jogadores = times.get(nomeTime.trim());
        if (jogadores == null) {
            return false;
        }

        boolean removido = jogadores.removeIf(j -> j.getNome().equalsIgnoreCase(nomeJogador.trim()));
        if (removido) {
            salvarDados();
        }
        return removido;
    }

    public boolean editarJogador(String nomeTime, String nomeJogador, int novosGols, int novosJogos, int novasAssistencias) {
        Set<Jogador> jogadores = times.get(nomeTime);
        if (jogadores == null) {
            return false;
        }

        for (Jogador j : jogadores) {
            if (j.getNome().equalsIgnoreCase(nomeJogador)) {
                j.setGols(novosGols);
                j.setJogos(novosJogos);
                j.setAssistencias(novasAssistencias);
                salvarDados();
                return true;
            }
        }
        return false;
    }

    public void listarTimes() {
        if (times.isEmpty()) {
            System.out.println("\nNenhum time cadastrado.");
            return;
        }
        
        System.out.println("\n=== Times Cadastrados ===");
        List<String> timesOrdenados = new ArrayList<>(times.keySet());
        Collections.sort(timesOrdenados);
        
        for (String time : timesOrdenados) {
            int qtdJogadores = times.get(time).size();
            int totalGols = times.get(time).stream().mapToInt(Jogador::getGols).sum();
            System.out.printf("- %s (%d jogadores, %d gols)%n", time, qtdJogadores, totalGols);
        }
    }

    public void listarJogadores(String nomeTime) {
        Set<Jogador> jogadores = times.get(nomeTime);
        if (jogadores == null || jogadores.isEmpty()) {
            System.out.println("\nNenhum jogador encontrado nesse time.");
            return;
        }
        
        System.out.println("\n=== Jogadores do " + nomeTime + " ===");
        jogadores.forEach(System.out::println);
        
        int totalGols = jogadores.stream().mapToInt(Jogador::getGols).sum();
        System.out.printf("\nTotal de gols do time: %d%n", totalGols);
    }

    public void buscarPorPosicao(String posicao) {
        System.out.println("\n=== Jogadores na posição: " + posicao + " ===");
        boolean encontrado = false;
        
        for (Map.Entry<String, Set<Jogador>> entry : times.entrySet()) {
            for (Jogador j : entry.getValue()) {
                if (j.getPosicao().equalsIgnoreCase(posicao)) {
                    System.out.printf("%s (Time: %s)%n", j, entry.getKey());
                    encontrado = true;
                }
            }
        }
        
        if (!encontrado) {
            System.out.println("\nNenhum jogador encontrado nessa posição.");
        }
    }

    public void exibirRankingGols() {
        List<Map.Entry<Jogador, String>> jogadoresComTime = new ArrayList<>();
        
        for (Map.Entry<String, Set<Jogador>> entry : times.entrySet()) {
            for (Jogador j : entry.getValue()) {
                jogadoresComTime.add(new AbstractMap.SimpleEntry<>(j, entry.getKey()));
            }
        }

        if (jogadoresComTime.isEmpty()) {
            System.out.println("\nNenhum jogador cadastrado ainda.");
            return;
        }

        jogadoresComTime.sort((e1, e2) -> {
            int cmpGols = Integer.compare(e2.getKey().getGols(), e1.getKey().getGols());
            if (cmpGols != 0) return cmpGols;
            return e1.getKey().getNome().compareToIgnoreCase(e2.getKey().getNome());
        });

        System.out.println("\n=== Ranking de Gols ===");
        int posicao = 1;
        for (Map.Entry<Jogador, String> entry : jogadoresComTime) {
            System.out.printf("%dº - %s (Time: %s)%n", posicao++, entry.getKey(), entry.getValue());
        }
    }

    public void exibirEstatisticas() {
        if (times.isEmpty()) {
            System.out.println("\nNenhum dado para exibir estatísticas.");
            return;
        }

        int totalTimes = times.size();
        int totalJogadores = times.values().stream().mapToInt(Set::size).sum();
        int totalGols = times.values().stream()
            .flatMap(Set::stream)
            .mapToInt(Jogador::getGols)
            .sum();

        System.out.println("\n=== Estatísticas Gerais ===");
        System.out.printf("Total de times: %d%n", totalTimes);
        System.out.printf("Total de jogadores: %d%n", totalJogadores);
        System.out.printf("Total de gols: %d%n", totalGols);
        if (totalJogadores > 0) {
            System.out.printf("Média de gols por jogador: %.2f%n", (double) totalGols / totalJogadores);
        }
    }

    public void buscarJogador(String nome) {
        System.out.println("\n=== Resultados da busca: '" + nome + "' ===");
        boolean encontrado = false;
        
        for (Map.Entry<String, Set<Jogador>> entry : times.entrySet()) {
            for (Jogador j : entry.getValue()) {
                if (j.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    System.out.printf("%s (Time: %s)%n", j, entry.getKey());
                    encontrado = true;
                }
            }
        }
        
        if (!encontrado) {
            System.out.println("\nNenhum jogador encontrado com esse nome.");
        }
    }

    private void salvarDados() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_DADOS))) {
            for (Map.Entry<String, Set<Jogador>> entry : times.entrySet()) {
                String nomeTime = entry.getKey();
                for (Jogador jogador : entry.getValue()) {
                    writer.println(nomeTime + ";" + jogador.toCSV());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", 2);
                if (partes.length >= 2) {
                    String nomeTime = partes[0];
                    Jogador jogador = Jogador.fromCSV(partes[1]);
                    times.putIfAbsent(nomeTime, new TreeSet<>());
                    times.get(nomeTime).add(jogador);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    public boolean timeExiste(String nomeTime) {
        return times.containsKey(nomeTime);
    }

    public Set<String> obterTimes() {
        return new TreeSet<>(times.keySet());
    }
}
