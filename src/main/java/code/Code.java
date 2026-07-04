package code;

import symboltable.SymbolTable;

/**
 * Encoding binário de instruções Hack (16 bits).
 *
 * @see docs/GUIA-MONTADOR.md
 */
public final class Code {

    private Code() {
    }

    // TODO: implementar (commits 5 e 6)

    public static String comp(String mnemonic) {
        throw new UnsupportedOperationException("TODO: implementar comp");
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
