# Checklist — Commits e Testes Unitários (Dupla)

Cada commit = **uma funcionalidade + testes unitários passando** antes de commitar.

Alternem **A** e **B**. Quem não commita revisa e roda `mvn test`.

| Símbolo | Significado |
|---------|-------------|
| **A** | Integrante 1 |
| **B** | Integrante 2 |
| ⬜ | Pendente · ✅ | Concluído |

---

## Pacote oficial de testes (Project 06)

Pasta local do Nand2Tetris (use estes `.asm` na validação final):

```
D:\Downloads\nand2tetris\nand2tetris\projects\6\
├── add\Add.asm          ← obrigatório
├── max\Max.asm          ← obrigatório
├── max\MaxL.asm         ← labels
├── rect\Rect.asm        ← recomendado
├── rect\RectL.asm
├── pong\Pong.asm        ← opcional
└── pong\PongL.asm
```

Detalhes de execução: [`TESTES-NAND2TETRIS.md`](TESTES-NAND2TETRIS.md)

---

## Mapa de commits (20 commits)

| # | Responsável | Escopo | Testes unitários obrigatórios | Status |
|---|-------------|--------|-------------------------------|--------|
| 1 | A | Estrutura Maven | `mvn compile` | ✅ |
| 2 | B | SymbolTable — predefinidos | `SymbolTableTest` (registradores, VM, I/O) | ✅ |
| 3 | B | SymbolTable — `addEntry` / labels | `shouldAddLabelEntry`, `shouldReturnNullForUnknown` | ✅ |
| 4 | A | SymbolTable — `contains` / `addVariable` | `shouldAllocateVariablesFrom16` | ✅ |
| 5 | A | Parser — leitura e comentários | `shouldStripComments`, `shouldSkipBlankLines` | ✅ |
| 6 | A | Parser — `advance` / `hasMoreInstructions` | `shouldAdvanceAndDetectEnd` | ✅ |
| 7 | B | Parser — `instructionType` | `shouldDetectA`, `shouldDetectC`, `shouldDetectLabel` | ✅ |
| 8 | B | Parser — `symbol()` | `shouldExtractAAndLabelSymbols` | ✅ |
| 9 | B | Parser — `dest` / `comp` / `jump` | `shouldParseCInstructionParts` | ✅ |
| 10 | A | Code — tabela `comp` | `CodeTest` → `shouldEncodeComp` | ✅ |
| 11 | A | Code — tabelas `dest` e `jump` | `shouldEncodeDest`, `shouldEncodeJump` | ✅ |
| 12 | B | Code — `encodeAInstruction` (números) | `shouldEncodeNumericA` | ✅ |
| 13 | B | Code — `encodeAInstruction` (símbolos) | `shouldEncodePredefinedAndVariable` | ✅ |
| 14 | A | Code — `encodeCInstruction` | `shouldEncodeFullCInstruction` | ⬜ |
| 15 | B | Main — passagem 1 (labels) | `MainTest.shouldCollectLabels` | ✅ |
| 16 | A | Main — passagem 2 (gerar `.hack`) | `MainTest.shouldGenerateBinaryLines` | ✅ |
| 17 | A | Main — CLI (`main`) | `MainTest.shouldWriteHackFile` | ✅ |
| 18 | B | Integração — `Add.asm` | `AssemblerIntegrationTest.shouldAssembleAdd` | ✅ |
| 19 | A | Integração — `Max.asm` + `MaxL.asm` | `shouldAssembleMax`, `shouldAssembleMaxL` | ✅ |
| 20 | B | Integração — `Rect.asm` + fixes | `shouldAssembleRect` | ✅ |
| 21 | A | Fix — ajustes pós-testes oficiais | testes que falharem | ⬜ |
| 22 | B | Docs — README + link vídeo | revisão manual | ⬜ |

> Mínimo exigido pela prova: **6 commits** · Meta da dupla: **20+ commits** granulares.

---

## Commits detalhados

### 1 · Estrutura inicial
| | |
|---|---|
| **A** | `chore: inicializa projeto Maven e estrutura de pastas` |
| Arquivos | `pom.xml`, `.gitignore`, stubs, `docs/` |
| Validar | `cd assembler && mvn compile` |

✅ Concluído

---

### 2 · SymbolTable — símbolos predefinidos
| | |
|---|---|
| **B** | `feat(symbol_table): adiciona símbolos predefinidos R0-R15 e ponteiros VM` |
| Implementar | Construtor: R0–R15, SP, LCL, ARG, THIS, THAT, SCREEN, KBD |
| Testes | `shouldInitializeRegisters`, `shouldInitializeVmPointers`, `shouldInitializeIoSymbols` |
| Validar | `mvn test -Dtest=SymbolTableTest#shouldInitialize*` |

✅ Concluído

---

### 3 · SymbolTable — labels
| | |
|---|---|
| **B** | `feat(symbol_table): implementa addEntry e getAddress` |
| Implementar | `addEntry(symbol, address)`, `getAddress(symbol)` |
| Testes | `shouldAddLabelEntry`, `shouldReturnNullForUnknownSymbol` |
| Validar | `mvn test -Dtest=SymbolTableTest#shouldAdd*` |

✅ Concluído

---

### 4 · SymbolTable — variáveis
| | |
|---|---|
| **A** | `feat(symbol_table): implementa contains e alocação de variáveis` |
| Implementar | `contains`, `addVariable` (RAM a partir de 16) |
| Testes | `shouldAllocateVariablesStartingAt16`, `shouldReuseExistingVariable` |
| Validar | `mvn test -Dtest=SymbolTableTest` |

✅ Concluído

---

### 5 · Parser — preprocessamento
| | |
|---|---|
| **A** | `feat(parser): implementa leitura de arquivo e remoção de comentários` |
| Implementar | Construtor, remove `//`, ignora linhas vazias |
| Testes | `shouldStripInlineComments`, `shouldSkipBlankLines`, `shouldSkipFullLineComments` |
| Validar | `mvn test -Dtest=ParserTest#shouldStrip*`, `#shouldSkip*` |

✅ Concluído

---

### 6 · Parser — navegação
| | |
|---|---|
| **A** | `feat(parser): implementa hasMoreInstructions e advance` |
| Implementar | Iteração linha a linha |
| Testes | `shouldAdvanceThroughInstructions`, `shouldDetectEndOfFile` |
| Validar | `mvn test -Dtest=ParserTest#shouldAdvance*` |

✅ Concluído

---

### 7 · Parser — tipos de instrução
| | |
|---|---|
| **B** | `feat(parser): identifica A-instruction, C-instruction e LABEL` |
| Implementar | `instructionType()` |
| Testes | `shouldDetectAInstruction`, `shouldDetectCInstruction`, `shouldDetectLabel` |
| Validar | `mvn test -Dtest=ParserTest#shouldDetect*` |

✅ Concluído

---

### 8 · Parser — symbol()
| | |
|---|---|
| **B** | `feat(parser): extrai símbolo de @valor e (ROTULO)` |
| Implementar | `symbol()` |
| Testes | `shouldExtractSymbolFromAInstruction`, `shouldExtractSymbolFromLabel` |
| Validar | `mvn test -Dtest=ParserTest#shouldExtract*` |

✅ Concluído

---

### 9 · Parser — partes da C-instruction
| | |
|---|---|
| **B** | `feat(parser): extrai dest, comp e jump de C-instructions` |
| Implementar | `dest()`, `comp()`, `jump()` |
| Testes | `shouldParseDestComp`, `shouldParseCompJumpOnly`, `shouldParseDestCompJump` |
| Casos | `D=M` · `D;JGT` · `AMD=M+1;JMP` |
| Validar | `mvn test -Dtest=ParserTest#shouldParse*` |

✅ Concluído

---

### 10 · Code — comp
| | |
|---|---|
| **A** | `feat(code): adiciona tabela de encoding comp` |
| Implementar | `comp(mnemonic)` — todos os mnemônicos do guia |
| Testes | `shouldEncodeCompZero`, `shouldEncodeCompWithM`, `shouldEncodeCompArithmetic` |
| Validar | `mvn test -Dtest=CodeTest#shouldEncodeComp*` |

✅ Concluído

---

### 11 · Code — dest e jump
| | |
|---|---|
| **A** | `feat(code): adiciona tabelas dest e jump` |
| Implementar | `dest()`, `jump()` |
| Testes | `shouldEncodeDest`, `shouldEncodeEmptyDest`, `shouldEncodeJump` |
| Validar | `mvn test -Dtest=CodeTest#shouldEncodeDest*`, `#shouldEncodeJump*` |

✅ Concluído

---

### 12 · Code — A-instruction numérica
| | |
|---|---|
| **B** | `feat(code): implementa encodeAInstruction para constantes` |
| Implementar | `@10` → `0000000000001010` (16 bits, bit 15 = 0) |
| Testes | `shouldEncodeNumericAInstruction`, `shouldPadTo16Bits` |
| Validar | `mvn test -Dtest=CodeTest#shouldEncodeNumeric*` |

✅

---

### 13 · Code — A-instruction simbólica
| | |
|---|---|
| **B** | `feat(code): resolve símbolos e variáveis em A-instructions` |
| Implementar | Predefinidos, labels, `addVariable` |
| Testes | `shouldEncodePredefinedSymbol`, `shouldEncodeLabel`, `shouldEncodeNewVariable` |
| Validar | `mvn test -Dtest=CodeTest#shouldEncodePredefined*`, `#shouldEncodeLabel*`, `#shouldEncodeNew*` |

✅

---

### 14 · Code — C-instruction completa
| | |
|---|---|
| **A** | `feat(code): implementa encodeCInstruction` |
| Implementar | `111` + comp(7) + dest(3) + jump(3) |
| Testes | `shouldEncodeDM`, `shouldEncodeMD`, `shouldEncodeDJump` |
| Referência | `D=M` → `1111110000010000` · `M=D` → `1110001100001000` |
| Validar | `mvn test -Dtest=CodeTest#shouldEncodeD*` |

⬜

---

### 15 · Main — passagem 1
| | |
|---|---|
| **B** | `feat(main): implementa primeira passagem para coletar labels` |
| Implementar | `collectLabels()` — `(LOOP)` → endereço ROM, labels não incrementam contador |
| Testes | `MainTest.shouldCollectLabelsFromMaxL` |
| Validar | `mvn test -Dtest=MainTest#shouldCollect*` |

✅

---

### 16 · Main — passagem 2
| | |
|---|---|
| **A** | `feat(main): implementa segunda passagem de geração binária` |
| Implementar | `generateCode()` — ignora labels, traduz A e C |
| Testes | `MainTest.shouldGenerateBinaryForAdd` |
| Validar | `mvn test -Dtest=MainTest#shouldGenerate*` |

✅

---

### 17 · Main — CLI e arquivo de saída
| | |
|---|---|
| **A** | `feat(main): implementa entrada CLI e escrita do arquivo .hack` |
| Implementar | `main(args)`, `assemble(path)` → `programa.hack` |
| Testes | `MainTest.shouldWriteHackFileToDisk` |
| Validar | `mvn test -Dtest=MainTest` |

✅

---

### 18 · Integração Add.asm
| | |
|---|---|
| **B** | `test: valida montagem de Add.asm do Nand2Tetris` |
| Arquivo | `D:\Downloads\nand2tetris\nand2tetris\projects\6\add\Add.asm` |
| Testes | `AssemblerIntegrationTest.shouldAssembleAdd` |
| Validar | `mvn test -Dtest=AssemblerIntegrationTest#shouldAssembleAdd` + CPU Emulator |

✅

---

### 19 · Integração Max.asm e MaxL.asm
| | |
|---|---|
| **A** | `test: valida montagem de Max.asm e MaxL.asm` |
| Arquivos | `projects\6\max\Max.asm`, `MaxL.asm` |
| Testes | `shouldAssembleMax`, `shouldAssembleMaxL` |
| Validar | CPU Emulator |

✅

---

### 20 · Integração Rect.asm
| | |
|---|---|
| **B** | `test: valida montagem de Rect.asm` |
| Arquivo | `projects\6\rect\Rect.asm` |
| Testes | `shouldAssembleRect` |
| Validar | CPU Emulator |

✅

---

### 21 · Correções
| | |
|---|---|
| **Quem achar** | `fix: corrige bugs encontrados nos testes oficiais` |
| Validar | `mvn clean test` + todos `.asm` obrigatórios |

⬜

---

### 22 · Documentação
| | |
|---|---|
| **B** | `docs(readme): adiciona integrantes, build e link do vídeo` |

⬜

---

## Ritual antes de cada commit

```bash
cd assembler
mvn test -Dtest=NomeDaClasseTest#nomeDoMetodo   # teste do commit atual
mvn test                                          # suite completa até aqui
git add src/main/java/... src/test/java/...
git commit -m "feat(escopo): descrição"
```

---

## Divisão equilibrada A / B

| Integrante | Commits |
|------------|---------|
| **A** | 1, 4, 5, 6, 10, 11, 14, 16, 17, 19, 21 |
| **B** | 2, 3, 7, 8, 9, 12, 13, 15, 18, 20, 22 |

---

## Entrega final

- [ ] Repositório `assembler` no GitHub/GitLab
- [ ] ≥ 6 commits descritivos (meta: 20+)
- [ ] `mvn clean test` 100% passando
- [ ] `Add.asm` e `Max.asm` no CPU Emulator
- [ ] `Rect.asm` recomendado
- [ ] README + vídeo ≤ 15 min
