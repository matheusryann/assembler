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
        throw new UnsupportedOperationException("TODO: implementar dest");
    }

    public static String jump(String mnemonic) {
        throw new UnsupportedOperationException("TODO: implementar jump");
    }

    public static String encodeAInstruction(String symbolOrValue, SymbolTable symbolTable) {
        throw new UnsupportedOperationException("TODO: implementar encodeAInstruction");
    }

    public static String encodeCInstruction(String destMnemonic, String compMnemonic, String jumpMnemonic) {
        throw new UnsupportedOperationException("TODO: implementar encodeCInstruction");
    }
}
