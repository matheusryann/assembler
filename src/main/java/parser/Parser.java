package parser;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Analisador de arquivos .asm.
 *
 * @see docs/GUIA-MONTADOR.md
 */
public class Parser {

    public enum InstructionType {
        A_INSTRUCTION,
        C_INSTRUCTION,
        LABEL
    }

    // TODO: implementar (commits 3 e 4)

    public Parser(Path filename) throws IOException {
        throw new UnsupportedOperationException("TODO: implementar construtor");
    }

    public boolean hasMoreInstructions() {
        throw new UnsupportedOperationException("TODO: implementar hasMoreInstructions");
    }

    public void advance() {
        throw new UnsupportedOperationException("TODO: implementar advance");
    }

    public InstructionType instructionType() {
        throw new UnsupportedOperationException("TODO: implementar instructionType");
    }

    public String symbol() {
        throw new UnsupportedOperationException("TODO: implementar symbol");
    }

    public String dest() {
        throw new UnsupportedOperationException("TODO: implementar dest");
    }

    public String comp() {
        throw new UnsupportedOperationException("TODO: implementar comp");
    }

    public String jump() {
        throw new UnsupportedOperationException("TODO: implementar jump");
    }
}
