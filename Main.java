import model.GerenciadorTimes;
import model.Jogador;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static GerenciadorTimes gerenciador = new GerenciadorTimes();

    public static void main(String[] args) {
        int opcao;

        System.out.println("=== BEM-VINDO AO GERENCIADOR DE TIMES ===");
        System.out.println("Sistema de gerenciamento de times e jogadores\n");

        do {
            exibirMenu();
            opcao = lerOpcao();

            try {
                switch (opcao) {
                    case 1:
                        adicionarTime();
                        break;
                    case 2:
                        adicionarJogador();
                        break;
                    case 3:
                        listarTimes();
                        break;
                    case 4:
                        listarJogadores();
                        break;
                    case 5:
                        buscarPorPosicao();
                        break;
                    case 6:
                        exibirRankingGols();
                        break;
                    case 7:
                        editarJogador();
                        break;
                    case 8:
                        removerJogador();
                        break;
                    case 9:
                        removerTime();
                        break;
                    case 10:
                        buscarJogador();
                        break;
                    case 11:
                        exibirEstatisticas();
                        break;
                    case 0:
                        System.out.println("\n✓ Dados salvos com sucesso!");
                        System.out.println("Encerrando programa... Até logo!");
                        break;
                    default:
                        System.out.println("\n✗ Opção inválida! Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\n✗ Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n✗ Erro inesperado: " + e.getMessage());
            }

            if (opcao != 0) {
                pausar();
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void exibirMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║    GERENCIADOR DE TIMES            ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1  - Adicionar Time               ║");
        System.out.println("║  2  - Adicionar Jogador            ║");
        System.out.println("║  3  - Listar Times                 ║");
        System.out.println("║  4  - Listar Jogadores de um Time  ║");
        System.out.println("║  5  - Buscar por Posição           ║");
        System.out.println("║  6  - Ranking de Gols              ║");
        System.out.println("║  7  - Editar Jogador               ║");
        System.out.println("║  8  - Remover Jogador              ║");
        System.out.println("║  9  - Remover Time                 ║");
        System.out.println("║  10 - Buscar Jogador por Nome      ║");
        System.out.println("║  11 - Estatísticas Gerais          ║");
        System.out.println("║  0  - Sair                         ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer
            return opcao;
        } catch (InputMismatchException e) {
            sc.nextLine(); // limpar buffer
            return -1;
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = sc.nextInt();
                sc.nextLine(); // limpar buffer
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine(); // limpar buffer
                System.out.println("✗ Por favor, digite um número válido!");
            }
        }
    }

    private static void adicionarTime() {
        System.out.println("\n--- ADICIONAR TIME ---");
        System.out.print("Nome do time: ");
        String nomeTime = sc.nextLine();

        try {
            if (gerenciador.adicionarTime(nomeTime)) {
                System.out.println("\n✓ Time '" + nomeTime + "' adicionado com sucesso!");
            } else {
                System.out.println("\n✗ Time '" + nomeTime + "' já existe no sistema!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ " + e.getMessage());
        }
    }

    private static void adicionarJogador() {
        System.out.println("\n--- ADICIONAR JOGADOR ---");
        
        if (gerenciador.obterTimes().isEmpty()) {
            System.out.println("✗ Nenhum time cadastrado! Cadastre um time primeiro.");
            return;
        }

        System.out.print("Nome do time: ");
        String timeJogador = sc.nextLine();

        if (!gerenciador.timeExiste(timeJogador)) {
            System.out.println("✗ Time não encontrado! Deseja criar o time? (S/N): ");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                gerenciador.adicionarTime(timeJogador);
                System.out.println("✓ Time criado!");
            } else {
                return;
            }
        }

        System.out.print("Nome do jogador: ");
        String nome = sc.nextLine();
        
        System.out.print("Posição: ");
        String posicao = sc.nextLine();
        
        int gols = lerInteiro("Número de gols: ");
        int jogos = lerInteiro("Número de jogos (0 se não souber): ");
        int assistencias = lerInteiro("Número de assistências (0 se não souber): ");

        try {
            Jogador jogador = new Jogador(nome, posicao, gols, jogos, assistencias);
            if (gerenciador.adicionarJogador(timeJogador, jogador)) {
                System.out.println("\n✓ Jogador '" + nome + "' adicionado com sucesso ao time " + timeJogador + "!");
            } else {
                System.out.println("\n✗ Jogador '" + nome + "' já existe neste time!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ " + e.getMessage());
        }
    }

    private static void listarTimes() {
        System.out.println("\n--- LISTAR TIMES ---");
        gerenciador.listarTimes();
    }

    private static void listarJogadores() {
        System.out.println("\n--- LISTAR JOGADORES ---");
        System.out.print("Nome do time: ");
        String timeListar = sc.nextLine();
        gerenciador.listarJogadores(timeListar);
    }

    private static void buscarPorPosicao() {
        System.out.println("\n--- BUSCAR POR POSIÇÃO ---");
        System.out.print("Posição (ex: Atacante, Zagueiro): ");
        String pos = sc.nextLine();
        gerenciador.buscarPorPosicao(pos);
    }

    private static void exibirRankingGols() {
        System.out.println("\n--- RANKING DE GOLS ---");
        gerenciador.exibirRankingGols();
    }

    private static void editarJogador() {
        System.out.println("\n--- EDITAR JOGADOR ---");
        System.out.print("Nome do time: ");
        String nomeTime = sc.nextLine();
        
        System.out.print("Nome do jogador: ");
        String nomeJogador = sc.nextLine();
        
        int novosGols = lerInteiro("Novo número de gols: ");
        int novosJogos = lerInteiro("Novo número de jogos: ");
        int novasAssistencias = lerInteiro("Novo número de assistências: ");

        if (gerenciador.editarJogador(nomeTime, nomeJogador, novosGols, novosJogos, novasAssistencias)) {
            System.out.println("\n✓ Jogador editado com sucesso!");
        } else {
            System.out.println("\n✗ Jogador ou time não encontrado!");
        }
    }

    private static void removerJogador() {
        System.out.println("\n--- REMOVER JOGADOR ---");
        System.out.print("Nome do time: ");
        String nomeTime = sc.nextLine();
        
        System.out.print("Nome do jogador: ");
        String nomeJogador = sc.nextLine();

        System.out.print("Tem certeza? (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (gerenciador.removerJogador(nomeTime, nomeJogador)) {
                System.out.println("\n✓ Jogador removido com sucesso!");
            } else {
                System.out.println("\n✗ Jogador ou time não encontrado!");
            }
        } else {
            System.out.println("\n✓ Operação cancelada.");
        }
    }

    private static void removerTime() {
        System.out.println("\n--- REMOVER TIME ---");
        System.out.print("Nome do time: ");
        String nomeTime = sc.nextLine();

        System.out.print("ATENÇÃO: Todos os jogadores serão removidos! Confirmar? (S/N): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (gerenciador.removerTime(nomeTime)) {
                System.out.println("\n✓ Time removido com sucesso!");
            } else {
                System.out.println("\n✗ Time não encontrado!");
            }
        } else {
            System.out.println("\n✓ Operação cancelada.");
        }
    }

    private static void buscarJogador() {
        System.out.println("\n--- BUSCAR JOGADOR ---");
        System.out.print("Digite o nome (ou parte do nome): ");
        String nome = sc.nextLine();
        gerenciador.buscarJogador(nome);
    }

    private static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS GERAIS ---");
        gerenciador.exibirEstatisticas();
    }

    private static void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
}
