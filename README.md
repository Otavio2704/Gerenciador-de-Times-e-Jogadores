# ⚽ Gerenciador de Times e Jogadores

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Ativo-success?style=for-the-badge)

Sistema completo em Java para gerenciamento de times de futebol e seus jogadores, com persistência de dados e interface de linha de comando intuitiva.

[Características](#-características) •
[Instalação](#-instalação) •
[Como Usar](#-como-usar) •
[Tecnologias](#-tecnologias) •
[Licença](#-licença)

</div>

---

## 📋 Características

### ✨ Funcionalidades Principais

- ✅ **Gerenciamento de Times**
  - Adicionar novos times ao sistema
  - Remover times com confirmação de segurança
  - Listar todos os times com estatísticas (jogadores e gols)

- 👤 **Gerenciamento de Jogadores**
  - Adicionar jogadores com informações completas (nome, posição, gols, jogos, assistências)
  - Editar estatísticas de jogadores existentes
  - Remover jogadores com confirmação
  - Buscar jogadores por nome (busca parcial)
  - Buscar jogadores por posição

- 📊 **Estatísticas e Rankings**
  - Ranking completo de artilheiros
  - Estatísticas gerais do sistema
  - Média de gols por jogador
  - Total de gols por time

- 💾 **Persistência de Dados**
  - Salvamento automático em arquivo
  - Carregamento automático ao iniciar
  - Dados preservados entre sessões

### 🛡️ Recursos de Segurança e Validação

- Validação de entrada de dados
- Tratamento de exceções robusto
- Confirmações antes de operações destrutivas
- Prevenção de duplicatas
- Mensagens de erro claras e informativas

---

## 🚀 Instalação

### Pré-requisitos

- Java JDK 8 ou superior
- Terminal/Prompt de comando

### Passos de Instalação

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

---

## 💻 Como Usar

### Menu Principal

Ao executar o programa, você verá o menu principal:

```
╔════════════════════════════════════╗
║    GERENCIADOR DE TIMES            ║
╠════════════════════════════════════╣
║  1  - Adicionar Time               ║
║  2  - Adicionar Jogador            ║
║  3  - Listar Times                 ║
║  4  - Listar Jogadores de um Time  ║
║  5  - Buscar por Posição           ║
║  6  - Ranking de Gols              ║
║  7  - Editar Jogador               ║
║  8  - Remover Jogador              ║
║  9  - Remover Time                 ║
║  10 - Buscar Jogador por Nome      ║
║  11 - Estatísticas Gerais          ║
║  0  - Sair                         ║
╚════════════════════════════════════╝
```

### Exemplos de Uso

#### 1. Adicionar um Time

```
Escolha uma opção: 1

--- ADICIONAR TIME ---
Nome do time: Flamengo

✓ Time 'Flamengo' adicionado com sucesso!
```

#### 2. Adicionar um Jogador

```
Escolha uma opção: 2

--- ADICIONAR JOGADOR ---
Nome do time: Flamengo
Nome do jogador: Gabriel Barbosa
Posição: Atacante
Número de gols: 25
Número de jogos (0 se não souber): 30
Número de assistências (0 se não souber): 8

✓ Jogador 'Gabriel Barbosa' adicionado com sucesso ao time Flamengo!
```

#### 3. Visualizar Ranking de Gols

```
Escolha uma opção: 6

--- RANKING DE GOLS ---

=== Ranking de Gols ===
1º - Gabriel Barbosa - Atacante (25 gols, 0.83 por jogo, 8 assist.) (Time: Flamengo)
2º - Pedro - Atacante (20 gols, 0.67 por jogo, 5 assist.) (Time: Flamengo)
```

#### 4. Estatísticas Gerais

```
Escolha uma opção: 11

--- ESTATÍSTICAS GERAIS ---

=== Estatísticas Gerais ===
Total de times: 3
Total de jogadores: 15
Total de gols: 120
Média de gols por jogador: 8.00
```

---

## 🛠️ Tecnologias Utilizadas

### Linguagem e Frameworks
- **Java 8+** - Linguagem principal do projeto

### Estruturas de Dados
- **HashMap** - Armazenamento eficiente de times e jogadores
- **TreeSet** - Ordenação automática de jogadores por nome
- **ArrayList** - Manipulação de listas dinâmicas

### Conceitos Aplicados
- **Collections Framework** - Uso avançado de coleções Java
- **Comparable Interface** - Implementação de ordenação customizada
- **Serializable** - Suporte para persistência de objetos
- **I/O Streams** - Leitura e escrita de arquivos
- **Exception Handling** - Tratamento robusto de erros
- **Programação Orientada a Objetos** - Encapsulamento, herança e polimorfismo

---

## 📁 Estrutura do Projeto

```
Gerenciador-de-Times-e-Jogadores/
│
├── Main.java                      # Classe principal com interface do usuário
│
├── model/
│   ├── GerenciadorTimes.java     # Lógica de negócio e persistência
│   └── Jogador.java              # Modelo de dados do jogador
│
├── times_dados.txt               # Arquivo de persistência (gerado automaticamente)
│
├── .gitignore                    # Arquivos ignorados pelo Git
├── LICENSE                       # Licença MIT
└── README.md                     # Este arquivo
```

---

## 🎯 Características Técnicas

### Classe `Jogador`
- Implementa `Comparable<Jogador>` para ordenação alfabética
- Implementa `Serializable` para persistência
- Sobrescreve `equals()` e `hashCode()` baseado no nome
- Validações completas de dados
- Métodos auxiliares: `toCSV()` e `fromCSV()`
- Cálculo automático de média de gols

### Classe `GerenciadorTimes`
- Utiliza `Map<String, Set<Jogador>>` para estrutura de dados
- `TreeSet` garante ordenação automática
- Persistência automática em arquivo texto
- Métodos com retorno booleano para verificação de operações
- Tratamento de erros em I/O
- Verificações de dados nulos e vazios

### Classe `Main`
- Interface de usuário via terminal
- Tratamento de `InputMismatchException`
- Confirmações de segurança
- Feedback visual com símbolos ✓ e ✗
- Sistema de pausas entre operações

---

## 📊 Formato de Persistência

Os dados são salvos no arquivo `times_dados.txt` no formato CSV:

```
NomeDoTime;NomeJogador;Posicao;Gols;Jogos;Assistencias
```

**Exemplo:**
```
Flamengo;Gabriel Barbosa;Atacante;25;30;8
Flamengo;Pedro;Atacante;20;28;5
```

---

## 🔄 Fluxo de Dados

```
┌─────────────┐
│   Usuário   │
└──────┬──────┘
       │
       ▼
┌─────────────────┐
│   Main.java     │  ← Interface do usuário
│  (Menu/Input)   │
└────────┬────────┘
         │
         ▼
┌──────────────────────┐
│ GerenciadorTimes.java│  ← Lógica de negócio
│   (Validações/CRUD)  │
└──────────┬───────────┘
           │
     ┌─────┴─────┐
     │           │
     ▼           ▼
┌─────────┐  ┌──────────────┐
│Jogador  │  │times_dados.txt│
│ .java   │  │ (Persistência)│
└─────────┘  └──────────────┘
```

---

## 🚦 Validações Implementadas

| Campo | Validação |
|-------|-----------|
| Nome do Time | Não pode ser vazio ou nulo |
| Nome do Jogador | Mínimo 2 caracteres, não pode ser vazio |
| Posição | Não pode ser vazia |
| Gols | Não pode ser negativo |
| Jogos | Não pode ser negativo |
| Assistências | Não pode ser negativo |

---

## 🎓 Conceitos de POO Aplicados

- **Encapsulamento**: Atributos privados com getters e setters
- **Validação**: Métodos privados de validação na classe Jogador
- **Abstração**: Separação entre modelo de dados e lógica de negócio
- **Coesão**: Cada classe tem uma responsabilidade bem definida
- **Imutabilidade Parcial**: Nome do jogador não pode ser alterado após criação

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

```
MIT License

Copyright (c) 2025 Otavio2704

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

## 👨‍💻 Autor

**Otavio2704**

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Otavio2704)

---

## 🤝 Contribuindo

Contribuições são muito bem-vindas! Siga os passos:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

---

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões, por favor:

- Abra uma [Issue](https://github.com/Otavio2704/Gerenciador-de-Times-e-Jogadores/issues)
- Entre em contato através do GitHub

---

## 🌟 Mostre seu apoio

Se este projeto foi útil para você, considere dar uma ⭐ no repositório!

---

<div align="center">

**Desenvolvido com ❤️ e ☕ por Otavio2704**

</div>
