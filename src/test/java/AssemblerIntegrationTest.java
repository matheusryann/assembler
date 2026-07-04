import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Testes de integração com arquivos oficiais Nand2Tetris Project 06.
 *
 * @see docs/TESTES-NAND2TETRIS.md
 */
class AssemblerIntegrationTest {

    // Caminho padrão — sobrescreva com env NAND2TETRIS_PROJECT6 se necessário
    // D:\Downloads\nand2tetris\nand2tetris\projects\6

    // --- commit 18 (B) ---

    @Disabled("commit 18")
    @Test
    void shouldAssembleAdd() {
        // projects/6/add/Add.asm
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
}
