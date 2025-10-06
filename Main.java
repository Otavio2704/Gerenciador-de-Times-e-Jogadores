import model.GerenciadorTimes;
import model.Jogador;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciadorTimes gerenciador = new GerenciadorTimes();
        int opcao;

        do {
            System.out.println("\n=== GERENCIADOR DE TIMES ===");
            System.out.println("1 - Adicionar Time");
            System.out.println("2 - Adicionar Jogador");
            System.out.println("3 - Listar Times");
            System.out.println("4 - Listar Jogadores de um Time");
            System.out.println("5 - Buscar Jogadores por Posição");
            System.out.println("6 - Exibir Ranking de Gols");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("\nNome do time: ");
                    String nomeTime = sc.nextLine();
                    gerenciador.adicionarTime(nomeTime);
                    System.out.println("\nTime adicionado com sucesso!");
                    break;

                case 2:
                    System.out.print("\nNome do time: ");
                    String timeJogador = sc.nextLine();
                    System.out.print("Nome do jogador: ");
                    String nome = sc.nextLine();
                    System.out.print("Posição: ");
                    String posicao = sc.nextLine();
                    System.out.print("Número de gols: ");
                    int gols = sc.nextInt();
                    sc.nextLine();
                    Jogador jogador = new Jogador(nome, posicao, gols);
                    gerenciador.adicionarJogador(timeJogador, jogador);
                    System.out.println("\nJogador adicionado com sucesso!");
                    break;

                case 3:
                    gerenciador.listarTimes();
                    break;

                case 4:
                    System.out.print("\nNome do time: ");
                    String timeListar = sc.nextLine();
                    gerenciador.listarJogadores(timeListar);
                    break;

                case 5:
                    System.out.print("\nPosição: ");
                    String pos = sc.nextLine();
                    gerenciador.buscarPorPosicao(pos);
                    break;

                case 6:
                    gerenciador.exibirRankingGols();
                    break;

                case 0:
                    System.out.println("\nEncerrando programa...");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
