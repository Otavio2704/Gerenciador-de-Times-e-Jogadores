package model;

import java.util.Objects;

public class Jogador implements Comparable<Jogador> {
    private String nome;
    private String posicao;
    private int gols;

    public Jogador(String nome, String posicao, int gols) {
        this.nome = nome;
        this.posicao = posicao;
        this.gols = gols;
    }

    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    @Override
    public int compareTo(Jogador outro) {
        // Ordenação alfabética por nome
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogador)) return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(nome, jogador.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return String.format("\n%s - %s (%d gols)", nome, posicao, gols);
    }
}
