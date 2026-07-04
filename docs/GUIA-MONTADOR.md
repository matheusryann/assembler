# Assembler (Montador) — Guia de Referência

> Material de apoio baseado no Chapter 06 (Nand2Tetris) e nas anotações da disciplina.  
> Use junto com `docs/CHECKLIST.md` durante a implementação.

---

## 1. Introdução: O Assembler na Hierarquia da Computação

### 1.1 Posicionamento estratégico

O **montador (assembler)** é a ponte entre código simbólico (legível) e código de máquina binário (executável pela CPU).

No pipeline clássico de compiladores (Aho & Ullman):

- **Front-end**: análise léxica, sintática e semântica
- **Back-end**: otimização e geração de código

O montador **finaliza o back-end**, convertendo assembly em bits que a CPU executa.

### Hierarquia completa de tradução

```
┌──────────────────────────────────────────────────────────────┐
│  1. Código Fonte (Jack, C, Python)                           │
│     Legibilidade: MÁXIMA                                     │
└────────────────┬─────────────────────────────────────────────┘
                 │ Compilador (Projects anteriores)
                 ↓
┌──────────────────────────────────────────────────────────────┐
│  2. Código VM (push constant 5, add, pop local 0)            │
└────────────────┬─────────────────────────────────────────────┘
                 │ VM Translator
                 ↓
┌──────────────────────────────────────────────────────────────┐
│  3. Assembly Hack (@5, D=A, M=D)  ← SEU DOMÍNIO              │
└────────────────┬─────────────────────────────────────────────┘
                 │ MONTADOR (Project 6) ← VOCÊ ESTÁ AQUI
                 ↓
┌──────────────────────────────────────────────────────────────┐
│  4. Código de Máquina (0000000000000101)                     │
│     Execução nativa na CPU Hack                              │
└──────────────────────────────────────────────────────────────┘
```

### 1.2 Três níveis de abstração

| Característica | Alto nível | Assembly | Máquina |
|----------------|------------|----------|---------|
| Legibilidade | Alta | Baixa | Nula |
| Abstração HW | Total | Mínima | Nenhuma |
| Dependência de arquitetura | Baixa | Alta | Total |
| Exemplo `x = y + 10` | `x = y + 10;` | `@y` `D=M` `@10` `D=D+A` `@x` `M=D` | sequência de 16 bits |

---

## 2. Anatomia da Linguagem Assembly Hack

### 2.1 Contexto

| Aspecto | Valor |
|---------|-------|
| Largura de palavra | 16 bits |
| Memória | 32K RAM + 32K ROM |
| Registradores | A (endereço), D (dados), M (RAM[A]) |
| Tipos de instrução | A-instruction, C-instruction (+ labels) |

### 2.2 A-Instruction: `@valor`

**Propósito:** carregar valor de 16 bits no registrador A.

**Formato binário:**

```
0 VVVVVVVVVVVVVVV
↑
bit 15 = 0 (marcador A-instruction)
```

**Exemplos:**

| Sintaxe | Significado | Binário (exemplo) |
|---------|-------------|-------------------|
| `@10` | Constante 10 | `0000000000001010` |
| `@KBD` | Símbolo predefinido | `0110000000000000` |
| `@LOOP` | Endereço do rótulo | depende da passagem 1 |
| `@variavel` | Variável (RAM ≥ 16) | alocado na passagem 2 |

### 2.2 C-Instruction: `dest=comp;jump`

**Formato binário:**

```
1 1 1  a  c1 c2 c3 c4 c5 c6  d1 d2 d3  j1 j2 j3
↑ ↑ ↑  ↑     comp (7 bits)      dest      jump
opcode C-instruction
```

**Ordem na string final:** `111` + **comp(7)** + **dest(3)** + **jump(3)** = 16 bits

### Campo `comp` (7 bits)

| Mnemônico | Binário | Mnemônico | Binário |
|-----------|---------|-----------|---------|
| `0` | `0101010` | `D` | `0001100` |
| `1` | `0111111` | `A` | `0110000` |
| `-1` | `0111010` | `M` | `1110000` |
| `!D` | `0001101` | `!A` | `0110001` |
| `!M` | `1110001` | `-D` | `0001111` |
| `-A` | `0110011` | `-M` | `1110011` |
| `D+1` | `0011111` | `A+1` | `0110111` |
| `M+1` | `1110111` | `D-1` | `0001110` |
| `A-1` | `0110010` | `M-1` | `1110010` |
| `D+A` | `0000010` | `D+M` | `1000010` |
| `D-A` | `0010011` | `D-M` | `1010011` |
| `A-D` | `0000111` | `M-D` | `1000111` |
| `D&A` | `0000000` | `D&M` | `1000000` |
| `D\|A` | `0010101` | `D\|M` | `1010101` |

> Quando o operando usa **M** (memória), o bit `a` do comp = 1 (primeiro bit dos 7 bits de comp).

### Campo `dest` (3 bits)

| Mnemônico | Binário | Efeito |
|-----------|---------|--------|
| (vazio) | `000` | Descarta resultado |
| `M` | `001` | RAM[A] |
| `D` | `010` | Registrador D |
| `MD` | `011` | D e RAM[A] |
| `A` | `100` | Registrador A |
| `AM` | `101` | A e RAM[A] |
| `AD` | `110` | A e D |
| `AMD` | `111` | A, D e RAM[A] |

### Campo `jump` (3 bits)

| Mnemônico | Binário | Condição |
|-----------|---------|----------|
| (vazio) | `000` | Sem salto |
| `JGT` | `001` | comp > 0 |
| `JEQ` | `010` | comp = 0 |
| `JGE` | `011` | comp ≥ 0 |
| `JLT` | `100` | comp < 0 |
| `JNE` | `101` | comp ≠ 0 |
| `JLE` | `110` | comp ≤ 0 |
| `JMP` | `111` | Sempre |

**Exemplos codificados:**

| Instrução | Binário |
|-----------|---------|
| `D=M` | `1111110000010000` |
| `M=D` | `1110001100001000` |
| `D=D+M` | `1111000010010000` |
| `D;JGT` | `1110001100000001` |
| `0;JMP` | `1110101010000111` |

---

## 3. Símbolos

### 3.1 Predefinidos

| Símbolo | Endereço |
|---------|----------|
| R0–R15 | 0–15 |
| SP, LCL, ARG, THIS, THAT | 0–4 |
| SCREEN | 16384 |
| KBD | 24576 |

### 3.2 Rótulos `(NOME)`

- Pseudo-instrução: **não gera código binário**
- Associa `NOME` ao endereço ROM da **próxima** instrução real
- Registrados na **passagem 1**

### 3.3 Variáveis

- Qualquer `@simbolo` que não é número nem predefinido nem rótulo
- Alocadas a partir de **RAM 16** na passagem 2
- Primeira variável → 16, segunda → 17, etc.

---

## 4. O problema: referência futura

```
@END        ← ainda não sabemos o endereço de END
0;JMP
...
(END)       ← END definido aqui
@R0
M=0
```

**Solução:** montagem em **duas passadas**.

---

## 5. Algoritmo de duas passadas

### 5.1 Passagem 1 — Tabela de símbolos (rótulos)

```
symTable = SymbolTable()   // já com predefinidos
romAddress = 0

para cada instrução:
    se LABEL:
        symTable.addEntry(nome, romAddress)
        // NÃO incrementa romAddress
    senão (A ou C):
        romAddress += 1
```

### 5.2 Passagem 2 — Geração do `.hack`

```
para cada instrução:
    se LABEL:
        ignorar
    se A-instruction @X:
        se X é número → endereco = int(X)
        senão se symTable.contains(X) → endereco = getAddress(X)
        senão → endereco = addVariable(X)
        escrever "0" + bin(endereco, 15 bits)
    se C-instruction:
        escrever "111" + comp(comp) + dest(dest) + jump(jump)
```

### 5.3 Fluxograma

```
INÍCIO → Ler .asm
    → PASSADA 1: coletar labels → SymbolTable
    → PASSADA 2: traduzir A/C → escrever .hack
FIM
```

---

## 6. Arquitetura modular (4 módulos)

### Módulo 1: Parser

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `Parser(path)` | — | Abre e preprocessa `.asm` |
| `hasMoreInstructions()` | boolean | Há mais linhas? |
| `advance()` | void | Próxima instrução |
| `instructionType()` | enum | A, C ou LABEL |
| `symbol()` | String | Valor de `@x` ou `(LOOP)` |
| `dest()` | String | Parte dest de C |
| `comp()` | String | Parte comp de C |
| `jump()` | String | Parte jump de C |

### Módulo 2: SymbolTable

| Método | Descrição |
|--------|-----------|
| `addEntry(symbol, address)` | Registra rótulo ou variável |
| `contains(symbol)` | Símbolo existe? |
| `getAddress(symbol)` | Endereço numérico |
| `addVariable(symbol)` | Aloca RAM 16+ se novo |

### Módulo 3: Code

| Método | Descrição |
|--------|-----------|
| `comp(mnemonic)` | 7 bits |
| `dest(mnemonic)` | 3 bits |
| `jump(mnemonic)` | 3 bits |
| `encodeAInstruction(symbol, table)` | 16 bits |
| `encodeCInstruction(dest, comp, jump)` | 16 bits |

### Módulo 4: Main

Orquestra passagem 1 + passagem 2; recebe `programa.asm`, gera `programa.hack`.

---

## 7. Exemplo completo: soma 1+2+…+n

Trecho simplificado:

```asm
@R0
D=M
@i
M=D
@sum
M=0
(LOOP)
@i
D=M
@END
D;JEQ
...
(END)
@sum
D=M
@R1
M=D
```

**Passagem 1 — tabela parcial:**

| Código | romAddr | Ação |
|--------|---------|------|
| `@R0` | 0 | → 1 |
| `D=M` | 1 | → 2 |
| `(LOOP)` | 6 | registra LOOP→6 |
| `(END)` | 20 | registra END→20 |

**Passagem 2 — trechos:**

| Instrução | Binário |
|-----------|---------|
| `@R0` | `0000000000000000` |
| `D=M` | `1111110000010000` |
| `@i` (1ª vez) | `0000000000010000` (i→16) |
| `M=D` | `1110001100001000` |
| `@END` | `0000000000010100` (20) |
| `D;JEQ` | `1110001100000010` |

---

## 8. Conexão Nand2Tetris (Projects 1–6)

```
Portas lógicas → Circuitos → Linguagem máquina → CPU Hack → MONTADOR → .hack executável
```

Sem o montador, seria necessário programar diretamente em binário.

---

## 9. Dicas de implementação

1. **Comece simples:** A-instructions e C básicas antes de labels
2. **Teste incremental:** `add.asm` antes de `maxL.asm`
3. **Duas passagens são obrigatórias** para labels
4. **Use tabelas** (Map) para COMP/DEST/JUMP — evita erros manuais
5. **Formato A:** sempre 16 caracteres, começando com `0`
6. **Labels não incrementam** o contador ROM na passagem 1
7. **Variáveis só na passagem 2** — ordem de aparição define endereço

---

## 10. Referências

- *The Elements of Computing Systems*, Cap. 6 — Nisan & Schocken
- Chapter 06 Lecture (PDF)
- Pacote oficial Project 06 (Nand2Tetris)
