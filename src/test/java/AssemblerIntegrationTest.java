import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Testes de integração com arquivos oficiais Nand2Tetris Project 06.
 *
 * @see docs/TESTES-NAND2TETRIS.md
 */
class AssemblerIntegrationTest {
    private static final Path NAND2TETRIS_PROJECT6 = Path.of(System.getenv().getOrDefault(
            "NAND2TETRIS_PROJECT6",
            "D:/Downloads/nand2tetris/nand2tetris/projects/6"
    ));

    // Caminho padrão — sobrescreva com env NAND2TETRIS_PROJECT6 se necessário
    // D:\Downloads\nand2tetris\nand2tetris\projects\6

    // --- commit 18 (B) ---

    @Test
    void shouldAssembleAdd(@TempDir Path tempDir) throws IOException {
        Path asmFile = copyProgramToTemp(tempDir, "Add.asm", NAND2TETRIS_PROJECT6.resolve("add/Add.asm"),
                Path.of("nand2tetris/projects/6/add/Add.asm"));
        Path hackFile = tempDir.resolve("Add.hack");

        Main.assemble(asmFile);

        assertTrue(Files.exists(hackFile));
        assertEquals(List.of(
                "0000000000000010",
                "1110110000010000",
                "0000000000000011",
                "1110000010010000",
                "0000000000000000",
                "1110001100001000"
        ), Files.readAllLines(hackFile));
    }

    // --- commit 19 (A) ---

    @Disabled("commit 19")
    @Test
    void shouldAssembleMax() {
        // projects/6/max/Max.asm
    }

    @Disabled("commit 19")
    @Test
    void shouldAssembleMaxL() {
        // projects/6/max/MaxL.asm — valida labels
    }

    // --- commit 20 (B) ---

    @Disabled("commit 20")
    @Test
    void shouldAssembleRect() {
        // projects/6/rect/Rect.asm
    }

    @Disabled("commit 20 — opcional")
    @Test
    void shouldAssemblePong() {
        // projects/6/pong/Pong.asm
    }

    private static Path copyProgramToTemp(Path tempDir, String filename, Path officialPath, Path fallbackPath)
            throws IOException {
        Path source = Files.exists(officialPath) ? officialPath : fallbackPath;
        Path destination = tempDir.resolve(filename);
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        return destination;
    }
}
