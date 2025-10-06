package model;

import java.util.*;

public class GerenciadorTimes {
    private Map<String, Set<Jogador>> times;

    public GerenciadorTimes() {
        times = new HashMap<>();
    }

    public void adicionarTime(String nomeTime) {
        times.putIfAbsent(nomeTime, new TreeSet<>());
    }

    public void adicionarJogador(String nomeTime, Jogador jogador) {
        times.putIfAbsent(nomeTime, new TreeSet<>());
        times.get(nomeTime).add(jogador);
    }

    public void listarTimes() {
        if (times.isEmpty()) {
            System.out.println("\nNenhum time cadastrado.");
            return;
        }
        System.out.println("\n=== Times Cadastrados ===");
        for (String time : times.keySet()) {
            System.out.println("- " + time);
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
    }

    public void buscarPorPosicao(String posicao) {
        System.out.println("\n=== Jogadores na posição: " + posicao + " ===");
        boolean encontrado = false;
        for (Set<Jogador> jogadores : times.values()) {
            for (Jogador j : jogadores) {
                if (j.getPosicao().equalsIgnoreCase(posicao)) {
                    System.out.println(j);
                    encontrado = true;
                }
            }
        }
        if (!encontrado) System.out.println("\nNenhum jogador encontrado nessa posição.");
    }

    public void exibirRankingGols() {
        Set<Jogador> ranking = new TreeSet<>(Comparator.comparingInt(Jogador::getGols).reversed());
        for (Set<Jogador> jogadores : times.values()) {
            ranking.addAll(jogadores);
        }

        if (ranking.isEmpty()) {
            System.out.println("\nNenhum jogador cadastrado ainda.");
            return;
        }

        System.out.println("\n=== Ranking de Gols ===");
        ranking.forEach(System.out::println);
    }
}
