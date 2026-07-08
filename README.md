# Assembler Hack - Nand2Tetris Project 6

Montador que traduz programas em linguagem Assembly Hack (`.asm`) para codigo
binario Hack (`.hack`), conforme o Project 6 do Nand2Tetris.

## Integrantes

| Nome completo |
|---------------|
| João Gabriel de Oliveira Lopes |
| Matheus Ryan Carreiro Costa |

## Linguagem e versoes

- Linguagem: Java
- Versao configurada no projeto: Java 17 (`maven.compiler.release=17`)
- Gerenciador de build: Maven
- Testes: JUnit 5

## Justificativa da linguagem

Java foi escolhido por permitir uma implementacao simples e organizada em
classes separadas para cada responsabilidade do assembler: leitura/parsing,
tabela de simbolos, codificacao das instrucoes e orquestracao das duas
passagens. O Maven tambem facilita a execucao dos testes automatizados.

## Estrutura do projeto

```text
assembler/
├── pom.xml
├── README.md
├── nand2tetris/
│   └── projects/6/
│       ├── add/Add.asm
│       ├── max/Max.asm
│       ├── max/MaxL.asm
│       ├── rect/Rect.asm
│       ├── rect/RectL.asm
│       ├── pong/Pong.asm
│       └── pong/PongL.asm
└── src/
    ├── main/java/
    │   ├── Main.java
    │   ├── parser/Parser.java
    │   ├── code/Code.java
    │   └── symboltable/SymbolTable.java
    └── test/java/
        ├── AssemblerIntegrationTest.java
        ├── MainTest.java
        ├── parser/ParserTest.java
        ├── code/CodeTest.java
        └── symboltable/SymbolTableTest.java
```

## Como fazer build

No terminal, a partir da raiz do projeto:

```powershell
mvn clean test
mvn package
```

O primeiro comando compila e executa todos os testes automatizados. O segundo
gera o arquivo executavel em:

```text
target/assembler-1.0.0.jar
```

## Como executar o assembler

Use o `.jar` gerado pelo Maven informando o caminho do arquivo `.asm`:

```powershell
java -jar target\assembler-1.0.0.jar nand2tetris\projects\6\add\Add.asm
```

O arquivo `.hack` e gerado no mesmo diretorio do arquivo `.asm`. Exemplo:

```text
nand2tetris/projects/6/add/Add.asm
nand2tetris/projects/6/add/Add.hack
```

Tambem e possivel executar diretamente pelas classes compiladas:

```powershell
java -cp target\classes Main nand2tetris\projects\6\max\Max.asm
```

## Fluxo de validacao

1. Traduza um arquivo `.asm` com o assembler.
2. Abra o CPU Emulator do Nand2Tetris.
3. Carregue o arquivo `.hack` gerado.
4. Execute o programa e verifique o comportamento esperado.

Exemplo:

```powershell
java -jar target\assembler-1.0.0.jar nand2tetris\projects\6\max\Max.asm
```

Depois, no CPU Emulator:

```text
File > Load Program > nand2tetris/projects/6/max/Max.hack
```

## Testes automatizados

Para executar toda a suite:

```powershell
mvn test
```

Para executar apenas os testes de integracao com os programas oficiais:

```powershell
mvn test -Dtest=AssemblerIntegrationTest
```

Programas oficiais validados:

| Programa | O que valida |
|----------|--------------|
| `Add.asm` | Instrucoes A e C basicas |
| `Max.asm` | Labels e desvios |
| `MaxL.asm` | Versao sem simbolos do Max |
| `Rect.asm` | Variaveis, labels, SCREEN e loop |

## Validacao manual no CPU Emulator

- `Add.hack`: resultado esperado em `RAM[0] = 5`.
- `Max.hack` / `MaxL.hack`: informe valores em `RAM[0]` e `RAM[1]`; o maior deve aparecer em `RAM[2]`.
- `Rect.hack`: informe uma altura em `RAM[0]`; o programa desenha um retangulo no canto superior esquerdo da tela.
- `Pong.hack`: o arquivo tambem monta corretamente e pode ser carregado no CPU Emulator.

Observacao: a pasta `nand2tetris/tools/` contem ferramentas locais do curso,
como o CPU Emulator, mas nao faz parte da entrega versionada do projeto.

## Video de apresentacao

[Link do video: \[preencher link\]](https://drive.google.com/file/d/1JlmkqFsKoHAuWpXISYaU8lsvPH8ou0bi/view?usp=drive_link)
