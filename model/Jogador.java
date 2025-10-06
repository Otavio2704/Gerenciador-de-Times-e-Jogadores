package model;

import java.io.Serializable;
import java.util.Objects;

public class Jogador implements Comparable<Jogador>, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String posicao;
    private int gols;
    private int jogos;
    private int assistencias;

    public Jogador(String nome, String posicao, int gols) {
        this(nome, posicao, gols, 0, 0);
    }

    public Jogador(String nome, String posicao, int gols, int jogos, int assistencias) {
        validarNome(nome);
        validarPosicao(posicao);
        validarNumero(gols, "gols");
        validarNumero(jogos, "jogos");
        validarNumero(assistencias, "assistências");
        
        this.nome = nome.trim();
        this.posicao = posicao.trim();
        this.gols = gols;
        this.jogos = jogos;
        this.assistencias = assistencias;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do jogador não pode ser vazio");
        }
        if (nome.trim().length() < 2) {
            throw new IllegalArgumentException("Nome do jogador deve ter pelo menos 2 caracteres");
        }
    }

    private void validarPosicao(String posicao) {
        if (posicao == null || posicao.trim().isEmpty()) {
            throw new IllegalArgumentException("Posição não pode ser vazia");
        }
    }

    private void validarNumero(int valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException(campo + " não pode ser negativo");
        }
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getGols() {
        return gols;
    }

    public int getJogos() {
        return jogos;
    }

    public int getAssistencias() {
        return assistencias;
    }

    // Setters com validação
    public void setGols(int gols) {
        validarNumero(gols, "gols");
        this.gols = gols;
    }

    public void setPosicao(String posicao) {
        validarPosicao(posicao);
        this.posicao = posicao.trim();
    }

    public void setJogos(int jogos) {
        validarNumero(jogos, "jogos");
        this.jogos = jogos;
    }

    public void setAssistencias(int assistencias) {
        validarNumero(assistencias, "assistências");
        this.assistencias = assistencias;
    }

    public double getMediaGols() {
        return jogos > 0 ? (double) gols / jogos : 0.0;
    }

    @Override
    public int compareTo(Jogador outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogador)) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(nome.toLowerCase(), jogador.nome.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n%s - %s (%d gols", nome, posicao, gols));
        if (jogos > 0) {
            sb.append(String.format(", %.2f por jogo", getMediaGols()));
        }
        if (assistencias > 0) {
            sb.append(String.format(", %d assist.", assistencias));
        }
        sb.append(")");
        return sb.toString();
    }

    public String toCSV() {
        return String.format("%s;%s;%d;%d;%d", nome, posicao, gols, jogos, assistencias);
    }

    public static Jogador fromCSV(String csv) {
        String[] partes = csv.split(";");
        if (partes.length >= 3) {
            String nome = partes[0];
            String posicao = partes[1];
            int gols = Integer.parseInt(partes[2]);
            int jogos = partes.length > 3 ? Integer.parseInt(partes[3]) : 0;
            int assistencias = partes.length > 4 ? Integer.parseInt(partes[4]) : 0;
            return new Jogador(nome, posicao, gols, jogos, assistencias);
        }
        throw new IllegalArgumentException("Formato CSV inválido");
    }
}
