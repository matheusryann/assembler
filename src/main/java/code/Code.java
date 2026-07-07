package code;

import java.util.Map;

import symboltable.SymbolTable;

/**
 * Encoding binário de instruções Hack (16 bits).
 *
 * @see docs/GUIA-MONTADOR.md
 */
public final class Code {

    private static final Map<String, String> COMP = Map.ofEntries(
            Map.entry("0", "0101010"),
            Map.entry("1", "0111111"),
            Map.entry("-1", "0111010"),
            Map.entry("D", "0001100"),
            Map.entry("A", "0110000"),
            Map.entry("M", "1110000"),
            Map.entry("!D", "0001101"),
            Map.entry("!A", "0110001"),
            Map.entry("!M", "1110001"),
            Map.entry("-D", "0001111"),
            Map.entry("-A", "0110011"),
            Map.entry("-M", "1110011"),
            Map.entry("D+1", "0011111"),
            Map.entry("A+1", "0110111"),
            Map.entry("M+1", "1110111"),
            Map.entry("D-1", "0001110"),
            Map.entry("A-1", "0110010"),
            Map.entry("M-1", "1110010"),
            Map.entry("D+A", "0000010"),
            Map.entry("D+M", "1000010"),
            Map.entry("D-A", "0010011"),
            Map.entry("D-M", "1010011"),
            Map.entry("A-D", "0000111"),
            Map.entry("M-D", "1000111"),
            Map.entry("D&A", "0000000"),
            Map.entry("D&M", "1000000"),
            Map.entry("D|A", "0010101"),
            Map.entry("D|M", "1010101")
    );

    private static final Map<String, String> DEST = Map.ofEntries(
            Map.entry("", "000"),
            Map.entry("M", "001"),
            Map.entry("D", "010"),
            Map.entry("MD", "011"),
            Map.entry("A", "100"),
            Map.entry("AM", "101"),
            Map.entry("AD", "110"),
            Map.entry("AMD", "111")
    );

    private static final Map<String, String> JUMP = Map.ofEntries(
            Map.entry("", "000"),
            Map.entry("JGT", "001"),
            Map.entry("JEQ", "010"),
            Map.entry("JGE", "011"),
            Map.entry("JLT", "100"),
            Map.entry("JNE", "101"),
            Map.entry("JLE", "110"),
            Map.entry("JMP", "111")
    );

    private Code() {
    }

    public static String comp(String mnemonic) {
        String bits = COMP.get(mnemonic);
        if (bits == null) {
            throw new IllegalArgumentException("Mnemônico comp desconhecido: " + mnemonic);
        }
        return bits;
    }

    public static String dest(String mnemonic) {
        String bits = DEST.get(mnemonic);
        if (bits == null) {
            throw new IllegalArgumentException("Mnemônico dest desconhecido: " + mnemonic);
        }
        return bits;
    }

    public static String jump(String mnemonic) {
        String bits = JUMP.get(mnemonic);
        if (bits == null) {
            throw new IllegalArgumentException("Mnemônico jump desconhecido: " + mnemonic);
        }
        return bits;
    }

    public static String encodeAInstruction(String symbolOrValue, SymbolTable symbolTable) {
        if (symbolOrValue == null) {
            throw new IllegalArgumentException("symbolOrValue não pode ser null");
        }

        // Literais numéricos: "10" → 0000000000001010
        if (symbolOrValue.matches("\\d+")) {
            int value = Integer.parseInt(symbolOrValue);
            if (value < 0 || value > 0x7FFF) {
                throw new IllegalArgumentException("Valor fora do alcance para A-instruction: " + value);
            }
            String bits = Integer.toBinaryString(value);
            return String.format("%16s", bits).replace(' ', '0');
        }

        // Símbolos: se já existe na tabela, usa o endereço; senão aloca variável a partir de 16.
        if (symbolTable == null) {
            throw new IllegalArgumentException("SymbolTable requerida para resolução de símbolos");
        }

        int address;
        if (symbolTable.contains(symbolOrValue)) {
            Integer addr = symbolTable.getAddress(symbolOrValue);
            if (addr == null) {
                throw new IllegalStateException("Símbolo presente, mas sem endereço: " + symbolOrValue);
            }
            address = addr;
        } else {
            address = symbolTable.addVariable(symbolOrValue);
        }

        String bits = Integer.toBinaryString(address);
        return String.format("%16s", bits).replace(' ', '0');
    }

    public static String encodeCInstruction(String destMnemonic, String compMnemonic, String jumpMnemonic) {
        if (compMnemonic == null) {
            throw new IllegalArgumentException("compMnemonic não pode ser null");
        }
        String compBits = comp(compMnemonic);
        String destBits = dest(destMnemonic == null ? "" : destMnemonic);
        String jumpBits = jump(jumpMnemonic == null ? "" : jumpMnemonic);
        return "111" + compBits + destBits + jumpBits;
    }
}
