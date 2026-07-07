import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import code.Code;
import parser.Parser;
import symboltable.SymbolTable;

/**
 * Orquestrador do montador — duas passagens sobre o arquivo .asm.
 *
 * @see docs/GUIA-MONTADOR.md
 */
public class Main {

    // TODO: implementar (commit 7)

    public static void assemble(Path inputFile) {
        throw new UnsupportedOperationException("TODO: implementar assemble");
    }

    public static List<String> generateCode(Path inputFile, SymbolTable symbolTable) throws IOException {
        Parser parser = new Parser(inputFile);
        List<String> binaryLines = new ArrayList<>();
        while (parser.hasMoreInstructions()) {
            parser.advance();
            switch (parser.instructionType()) {
                case A_INSTRUCTION:
                    binaryLines.add(Code.encodeAInstruction(parser.symbol(), symbolTable));
                    break;
                case C_INSTRUCTION:
                    binaryLines.add(Code.encodeCInstruction(parser.dest(), parser.comp(), parser.jump()));
                    break;
                case LABEL:
                    break;
                default:
                    throw new IllegalStateException("Tipo de instrução desconhecido");
            }
        }
        return binaryLines;
    }

    /**
     * Primeira passagem: percorre o arquivo e registra rótulos (labels) na tabela de símbolos
     * associando cada label ao endereço ROM atual.
     */
    public static void collectLabels(Path inputFile, SymbolTable symbolTable) throws IOException {
        Parser parser = new Parser(inputFile);
        int romAddress = 0;
        while (parser.hasMoreInstructions()) {
            parser.advance();
            switch (parser.instructionType()) {
                case A_INSTRUCTION:
                case C_INSTRUCTION:
                    romAddress++;
                    break;
                case LABEL:
                    String label = parser.symbol();
                    symbolTable.addEntry(label, romAddress);
                    break;
                default:
                    throw new IllegalStateException("Tipo de instrução desconhecido");
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java -jar assembler.jar <arquivo.asm>");
            System.exit(1);
        }

        System.err.println("Montador ainda não implementado. Veja docs/CHECKLIST.md");
        System.exit(1);
    }
}
