package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Analisador de arquivos .asm.
 *
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
        if (currentCommand.startsWith("@")) {
            return currentCommand.substring(1);
        }
        if (currentCommand.startsWith("(") && currentCommand.endsWith(")")) {
            return currentCommand.substring(1, currentCommand.length() - 1);
        }
        throw new IllegalStateException("symbol() só se aplica a A-instructions e labels");
    }

    public String dest() {
        String destComp = destCompPart();
        int equalsIndex = destComp.indexOf('=');
        if (equalsIndex == -1) {
            return "";
        }
        return destComp.substring(0, equalsIndex);
    }

    public String comp() {
        String destComp = destCompPart();
        int equalsIndex = destComp.indexOf('=');
        if (equalsIndex == -1) {
            return destComp;
        }
        return destComp.substring(equalsIndex + 1);
    }

    public String jump() {
        String[] parts = currentCommand.split(";", -1);
        if (parts.length < 2) {
            return "";
        }
        return parts[1];
    }

    private String destCompPart() {
        return currentCommand.split(";", -1)[0];
    }
}
