import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários do Main — passagens 1 e 2 (ver docs/CHECKLIST.md).
 */
class MainTest {

    // --- commit 15 (B) ---

    @Test
    void shouldCollectLabelsFromMaxL() {
        symboltable.SymbolTable table = new symboltable.SymbolTable();
        try {
            Main.collectLabels(java.nio.file.Path.of("src/test/resources/maxL.asm"), table);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
        org.junit.jupiter.api.Assertions.assertEquals(10, table.getAddress("IFGT"));
        org.junit.jupiter.api.Assertions.assertEquals(12, table.getAddress("OUTPUT"));
    }

    // --- commit 16 (A) ---

    @Test
    void shouldGenerateBinaryForAdd() {
        try {
            symboltable.SymbolTable table = new symboltable.SymbolTable();
            Main.collectLabels(java.nio.file.Path.of("src/test/resources/add.asm"), table);
            java.util.List<String> lines = Main.generateCode(java.nio.file.Path.of("src/test/resources/add.asm"), table);
            org.junit.jupiter.api.Assertions.assertEquals(6, lines.size());
            org.junit.jupiter.api.Assertions.assertEquals("0000000000000000", lines.get(0));
            org.junit.jupiter.api.Assertions.assertEquals("1111110000010000", lines.get(1));
            org.junit.jupiter.api.Assertions.assertEquals("0000000000000001", lines.get(2));
            org.junit.jupiter.api.Assertions.assertEquals("1111000010010000", lines.get(3));
            org.junit.jupiter.api.Assertions.assertEquals("0000000000000010", lines.get(4));
            org.junit.jupiter.api.Assertions.assertEquals("1110001100001000", lines.get(5));
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    // --- commit 17 (A) ---

    @Disabled("commit 17")
    @Test
    void shouldWriteHackFileToDisk() {
        // assemble(path) cria arquivo .hack no mesmo diretório
    }
}
