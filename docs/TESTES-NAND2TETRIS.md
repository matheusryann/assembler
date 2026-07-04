# Testes com pacote oficial Nand2Tetris (Project 06)

## Localização dos arquivos

```
D:\Downloads\nand2tetris\nand2tetris\projects\6\
├── add\
│   └── Add.asm
├── max\
│   ├── Max.asm
│   └── MaxL.asm
├── rect\
│   ├── Rect.asm
│   └── RectL.asm
└── pong\
    ├── Pong.asm
    └── PongL.asm
```

> Se a pasta estiver em outro disco/usuário, ajuste o caminho nos testes de integração ou use variável de ambiente `NAND2TETRIS_PROJECT6`.

---

## Prioridade de validação

| Arquivo | Obrigatoriedade | O que testa |
|---------|-----------------|-------------|
| `add/Add.asm` | **Obrigatório** | A + C básicas, sem labels |
| `max/Max.asm` | **Obrigatório** | Variáveis (@IFGT, @OUTPUT) |
| `max/MaxL.asm` | **Obrigatório** | Rótulos `(IFGT)`, `(OUTPUT)` |
| `rect/Rect.asm` | Recomendado | Loop + SCREEN |
| `pong/Pong.asm` | Opcional | Programa grande |

---

## Montar manualmente

```bash
cd assembler
mvn package

# Exemplos (ajuste o caminho se necessário)
java -jar target/assembler-1.0.0.jar "D:\Downloads\nand2tetris\nand2tetris\projects\6\add\Add.asm"
java -jar target/assembler-1.0.0.jar "D:\Downloads\nand2tetris\nand2tetris\projects\6\max\Max.asm"
java -jar target/assembler-1.0.0.jar "D:\Downloads\nand2tetris\nand2tetris\projects\6\max\MaxL.asm"
java -jar target/assembler-1.0.0.jar "D:\Downloads\nand2tetris\nand2tetris\projects\6\rect\Rect.asm"
```

Cada comando gera um `.hack` **no mesmo diretório** do `.asm` de entrada.

---

## Validar no CPU Emulator

1. Abra o **CPU Emulator** do Nand2Tetris
2. File → Load Program → selecione o `.hack` gerado
3. Run → verifique comportamento esperado

### Comportamento esperado (resumo)

| Programa | Entrada (RAM) | Saída esperada |
|----------|---------------|----------------|
| Add | R0=2, R1=3 | R2=5 |
| Max | R0 e R1 | R2 = max(R0, R1) |
| MaxL | idem Max | idem (com labels) |
| Rect | — | Desenha retângulo na tela |

---

## Usar nos testes de integração (JUnit)

Opção recomendada: copiar os `.asm` oficiais para `src/test/resources/nand2tetris/` **ou** ler via caminho configurável:

```java
private static final Path NAND2TETRIS_P6 = Path.of(
    System.getenv().getOrDefault(
        "NAND2TETRIS_PROJECT6",
        "D:/Downloads/nand2tetris/nand2tetris/projects/6"
    )
);
```

Exemplo no teste:

```java
Path addAsm = NAND2TETRIS_P6.resolve("add/Add.asm");
Main.assemble(addAsm);
List<String> lines = Files.readAllLines(addAsm.resolveSibling("Add.hack"));
assertFalse(lines.isEmpty());
assertTrue(lines.stream().allMatch(l -> l.matches("[01]{16}")));
```

---

## Comparar com referência (opcional)

Se tiver o `.hack` gerado pelo assembler oficial do curso:

```powershell
fc.exe "Add.hack" "Add-reference.hack"
```

Ou compare linha a linha no teste JUnit com arquivo em `src/test/resources/expected/add.hack` (vocês podem gerar uma vez com assembler correto e commitar como referência).

---

## Checklist de validação manual

- [ ] `Add.asm` → `.hack` com 6 linhas binárias
- [ ] `Max.asm` executa no emulador
- [ ] `MaxL.asm` executa no emulador (labels corretos)
- [ ] `Rect.asm` desenha na tela
- [ ] (Opcional) `Pong.asm` monta sem erro
