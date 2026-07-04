import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários do Main — passagens 1 e 2 (ver docs/CHECKLIST.md).
 */
class MainTest {

    // --- commit 15 (B) ---

    @Disabled("commit 15")
    @Test
    void shouldCollectLabelsFromMaxL() {
        // passagem 1: (IFGT) e (OUTPUT) com endereços corretos
    }

    // --- commit 16 (A) ---

    @Disabled("commit 16")
    @Test
    void shouldGenerateBinaryForAdd() {
        // passagem 2: add.asm → 6 linhas de 16 bits
    }

    // --- commit 17 (A) ---

    @Disabled("commit 17")
    @Test
    void shouldWriteHackFileToDisk() {
        // assemble(path) cria arquivo .hack no mesmo diretório
    }
}
