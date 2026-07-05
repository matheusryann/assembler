package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    private final List<String> commands;
    private int currentIndex = -1;
    private String currentCommand = "";

    public Parser(Path filename) throws IOException {
        commands = new ArrayList<>();
        for (String line : Files.readAllLines(filename)) {
            String cleaned = cleanLine(line);
            if (!cleaned.isEmpty()) {
                commands.add(cleaned);
            }
        }
    }

    private static String cleanLine(String line) {
        int commentIndex = line.indexOf("//");
        if (commentIndex >= 0) {
            line = line.substring(0, commentIndex);
        }
        return line.trim();
    }

    public boolean hasMoreInstructions() {
        return currentIndex + 1 < commands.size();
    }

    public void advance() {
        if (!hasMoreInstructions()) {
            throw new IllegalStateException("No more instructions");
        }
        currentIndex++;
        currentCommand = commands.get(currentIndex);
    }

    public InstructionType instructionType() {
        if (currentCommand.startsWith("@")) {
            return InstructionType.A_INSTRUCTION;
        }
        if (currentCommand.startsWith("(") && currentCommand.endsWith(")")) {
            return InstructionType.LABEL;
        }
        return InstructionType.C_INSTRUCTION;
    }

    public String symbol() {
        if (!currentCommand.startsWith("@")) {
            throw new UnsupportedOperationException("TODO: implementar symbol para labels");
        }
        return currentCommand.substring(1);
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
