# Checklist — Funcionalidades e Commits (Dupla)

Use este documento para dividir o trabalho. **Cada commit deve ser atômico**: uma funcionalidade + testes passando antes de commitar.

## Legenda

| Símbolo | Significado |
|---------|-------------|
| **A** | Integrante 1 |
| **B** | Integrante 2 |
| ✅ | Concluído |
| ⬜ | Pendente |

Alternem quem commita; o outro **revisa o commit** e roda `mvn test`.

---

## Visão geral das funcionalidades

| # | Módulo | Funcionalidade | Depende de |
|---|--------|----------------|------------|
| 1 | Projeto | Estrutura Maven + pastas | — |
| 2 | `symboltable` | Símbolos predefinidos + labels + variáveis | 1 |
| 3 | `parser` | Leitura, comentários, linhas vazias | 1 |
| 4 | `parser` | Tipos A / C / LABEL + dest/comp/jump | 3 |
| 5 | `code` | Encoding A-instruction | 2 |
| 6 | `code` | Encoding C-instruction (COMP/DEST/JUMP) | — |
| 7 | `main` | Duas passagens (assemble completo) | 2, 4, 5, 6 |
| 8 | Testes | Integração `add.asm` / `maxL.asm` | 7 |
| 9 | Fix | Testes oficiais Nand2Tetris | 8 |
| 10 | Docs | README final + link do vídeo | 9 |

---

## Commits planejados

### Commit 1 — Estrutura inicial

| | |
|---|---|
| **Responsável** | **A** |
| **Mensagem** | `chore: inicializa projeto Maven e estrutura de pastas` |
| **Arquivos** | `pom.xml`, `.gitignore`, pastas, classes stub, `docs/` |
| **Teste** | `mvn clean compile` |

⬜ Concluído

---

### Commit 2 — SymbolTable

| | |
|---|---|
| **Responsável** | **B** |
| **Mensagem** | `feat(symbol_table): implementa tabela de símbolos com predefinições` |
| **Implementar** | R0–R15, SP, LCL, ARG, THIS, THAT, SCREEN, KBD; `addEntry`, `contains`, `getAddress`, `addVariable` |
| **Testes** | `SymbolTableTest` — remover `@Disabled` |
| **Validar** | `mvn test -Dtest=SymbolTableTest` |

⬜ Concluído

---

### Commit 3 — Parser (básico)

| | |
|---|---|
| **Responsável** | **A** |
| **Mensagem** | `feat(parser): implementa leitura e filtragem de comentários` |
| **Implementar** | Ler `.asm`, remover `//`, ignorar vazias, `hasMoreInstructions`, `advance` |
| **Testes** | `ParserTest.shouldRemoveCommentsAndBlankLines` |
| **Validar** | `mvn test -Dtest=ParserTest` |

⬜ Concluído

---

### Commit 4 — Parser (tipos e componentes)

| | |
|---|---|
| **Responsável** | **B** |
| **Mensagem** | `feat(parser): identifica tipos A, C e LABEL` |
| **Implementar** | `instructionType`, `symbol`, `dest`, `comp`, `jump` |
| **Testes** | Restante de `ParserTest` |
| **Validar** | `mvn test -Dtest=ParserTest` |

⬜ Concluído

---

### Commit 5 — Code (A-instruction)

| | |
|---|---|
| **Responsável** | **A** |
| **Mensagem** | `feat(code): implementa encoding de A-instructions` |
| **Implementar** | `encodeAInstruction` — números, predefinidos, variáveis |
| **Testes** | Casos A em `CodeTest` |
| **Validar** | `mvn test -Dtest=CodeTest` |

⬜ Concluído

---

### Commit 6 — Code (C-instruction)

| | |
|---|---|
| **Responsável** | **B** |
| **Mensagem** | `feat(code): implementa encoding de C-instructions` |
| **Implementar** | Tabelas COMP/DEST/JUMP + `encodeCInstruction` |
| **Testes** | `CodeTest.shouldEncodeCInstruction` |
| **Validar** | `mvn test -Dtest=CodeTest` |

⬜ Concluído

---

### Commit 7 — Main (duas passagens)

| | |
|---|---|
| **Responsável** | **A** |
| **Mensagem** | `feat(main): implementa assembler em duas passagens` |
| **Implementar** | Passagem 1 (labels) + Passagem 2 (gerar `.hack`) |
| **Testes** | `AssemblerIntegrationTest` |
| **Validar** | `mvn test -Dtest=AssemblerIntegrationTest` |

⬜ Concluído

---

### Commit 8 — Testes de integração

| | |
|---|---|
| **Responsável** | **B** |
| **Mensagem** | `test: adiciona casos de integração add.asm e maxL.asm` |
| **Validar** | `mvn clean test` |

⬜ Concluído

---

### Commit 9 — Correções

| | |
|---|---|
| **Responsável** | Quem encontrar o bug |
| **Mensagem** | `fix: corrige bugs encontrados nos testes oficiais` |
| **Validar** | `add.asm`, `max.asm` no CPU Emulator |

⬜ Concluído

---

### Commit 10 — Documentação final

| | |
|---|---|
| **Responsável** | **B** |
| **Mensagem** | `docs(readme): adiciona integrantes, build e link do vídeo` |

⬜ Concluído

---

## Ritual antes de cada commit

```bash
mvn clean test
git add <arquivos desta funcionalidade>
git commit -m "tipo(escopo): descrição"
```

---

## Checklist final de entrega

- [ ] Repositório `assembler` no GitHub/GitLab
- [ ] Mínimo 6 commits descritivos
- [ ] `mvn clean test` passando
- [ ] `add.asm` e `max.asm` no CPU Emulator
- [ ] README + vídeo ≤ 15 min

---

## Divisão sugerida

| Sprint | A | B |
|--------|---|---|
| 1 | Commits 1, 3, 5, 7 | Commits 2, 4, 6, 8 |
| 2 | Testes oficiais + vídeo (juntos) | Commits 9, 10 |
