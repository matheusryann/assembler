import java.nio.file.Path;

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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java -jar assembler.jar <arquivo.asm>");
            System.exit(1);
        }

        System.err.println("Montador ainda não implementado. Veja docs/CHECKLIST.md");
        System.exit(1);
    }
}
