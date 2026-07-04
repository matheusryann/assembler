# Assembler Hack (Nand2Tetris – Project 6)

Montador que traduz `.asm` → `.hack`.

> Implementação incremental — veja `docs/CHECKLIST.md` e `docs/GUIA-MONTADOR.md`.

## Integrantes

| Nome | RA |
|------|-----|
| **[Seu Nome]** | **[RA]** |
| **[Colega]** | **[RA]** |

## Stack

Java 21+ · Maven 3.9+ · JUnit 5

## Estrutura

```
assembler/
├── pom.xml
├── README.md
├── docs/
│   ├── CHECKLIST.md
│   └── GUIA-MONTADOR.md
└── src/
    ├── main/java/
    │   ├── Main.java
    │   ├── parser/Parser.java
    │   ├── code/Code.java
    │   └── symboltable/SymbolTable.java
    └── test/java/
        ├── AssemblerIntegrationTest.java
        ├── parser/ParserTest.java
        ├── code/CodeTest.java
        └── symboltable/SymbolTableTest.java
```

## Build

```bash
cd assembler
mvn compile
mvn test
mvn package
java -jar target/assembler-1.0.0.jar programa.asm
```

## Documentação

- [Checklist de commits (22 commits)](docs/CHECKLIST.md)
- [Guia teórico](docs/GUIA-MONTADOR.md)
- [Testes Nand2Tetris Project 06](docs/TESTES-NAND2TETRIS.md)

## Testes oficiais

Pacote local:

```
D:\Downloads\nand2tetris\nand2tetris\projects\6\
```

| Arquivo | Prioridade |
|---------|------------|
| `add/Add.asm`, `max/Max.asm` | Obrigatório |
| `max/MaxL.asm` | Labels |
| `rect/Rect.asm` | Recomendado |
| `pong/Pong.asm` | Opcional |
