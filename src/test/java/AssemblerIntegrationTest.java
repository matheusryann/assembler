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

    @Test
    void shouldAssembleMax(@TempDir Path tempDir) throws IOException {
        Path asmFile = copyProgramToTemp(tempDir, "Max.asm", NAND2TETRIS_PROJECT6.resolve("max/Max.asm"),
                Path.of("nand2tetris/projects/6/max/Max.asm"));
        Path hackFile = tempDir.resolve("Max.hack");

        Main.assemble(asmFile);

        assertTrue(Files.exists(hackFile));
        assertEquals(expectedMaxBinary(), Files.readAllLines(hackFile));
    }

    @Test
    void shouldAssembleMaxL(@TempDir Path tempDir) throws IOException {
        Path asmFile = copyProgramToTemp(tempDir, "MaxL.asm", NAND2TETRIS_PROJECT6.resolve("max/MaxL.asm"),
                Path.of("nand2tetris/projects/6/max/MaxL.asm"));
        Path hackFile = tempDir.resolve("MaxL.hack");

        Main.assemble(asmFile);

        assertTrue(Files.exists(hackFile));
        assertEquals(expectedMaxBinary(), Files.readAllLines(hackFile));
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

    private static List<String> expectedMaxBinary() {
        return List.of(
                "0000000000000000",
                "1111110000010000",
                "0000000000000001",
                "1111010011010000",
                "0000000000001010",
                "1110001100000001",
                "0000000000000001",
                "1111110000010000",
                "0000000000001100",
                "1110101010000111",
                "0000000000000000",
                "1111110000010000",
                "0000000000000010",
                "1110001100001000",
                "0000000000001110",
                "1110101010000111"
        );
    }
}
