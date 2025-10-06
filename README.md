# ⚽ Gerenciador de Times e Jogadores

Sistema em Java para gerenciamento de times de futebol e seus jogadores, permitindo cadastro, consulta e ranking de artilheiros.

## 📋 Funcionalidades

- **Adicionar Times**: Cadastre novos times no sistema
- **Adicionar Jogadores**: Registre jogadores com nome, posição e número de gols
- **Listar Times**: Visualize todos os times cadastrados
- **Listar Jogadores**: Veja todos os jogadores de um time específico (ordenados alfabeticamente)
- **Buscar por Posição**: Encontre jogadores por posição (atacante, meio-campo, zagueiro, etc.)
- **Ranking de Gols**: Exiba os artilheiros em ordem decrescente de gols

## 🛠️ Tecnologias Utilizadas

- **Java** (Collections Framework)
- **HashMap**: Armazenamento de times e jogadores
- **TreeSet**: Ordenação automática de jogadores
- **Comparable**: Implementação de ordenação personalizada
- **Scanner**: Interface de linha de comando

## 📁 Estrutura do Projeto

```
├── Main.java                      # Classe principal com menu interativo
├── model/
│   ├── GerenciadorTimes.java     # Lógica de gerenciamento
│   └── Jogador.java              # Modelo de dados do jogador
├── .gitignore
├── LICENSE
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado
- Terminal/Prompt de comando

### Passos

1. **Clone o repositório**
```bash
git clone https://github.com/Otavio2704/Gerenciador-de-Times-e-Jogadores.git
cd Gerenciador-de-Times-e-Jogadores
```

2. **Compile o projeto**
```bash
javac Main.java model/*.java
```

3. **Execute a aplicação**
```bash
java Main
```

## 💻 Exemplo de Uso

```
=== GERENCIADOR DE TIMES ===
1 - Adicionar Time
2 - Adicionar Jogador
3 - Listar Times
4 - Listar Jogadores de um Time
5 - Buscar Jogadores por Posição
6 - Exibir Ranking de Gols
0 - Sair
Escolha: 1

Nome do time: Flamengo
Time adicionado com sucesso!

Escolha: 2

Nome do time: Sao Paulo
Nome do jogador: Jonathan Calleri
Posição: Atacante
Número de gols: 50
Jogador adicionado com sucesso!
```

## 🎯 Características Técnicas

### Classe Jogador
- Implementa `Comparable<Jogador>` para ordenação alfabética por nome
- Sobrescreve `equals()` e `hashCode()` baseado no nome do jogador
- Previne duplicatas de jogadores com mesmo nome no mesmo time

### Classe GerenciadorTimes
- Utiliza `Map<String, Set<Jogador>>` para relacionar times e jogadores
- `TreeSet` garante ordenação automática dos jogadores
- Métodos robustos com verificações de nulos e coleções vazias

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Otavio2704**

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela!
