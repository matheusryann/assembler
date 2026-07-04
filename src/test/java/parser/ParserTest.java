package parser;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários — um método por commit (ver docs/CHECKLIST.md).
 */
class ParserTest {

    // --- commit 5 (A) ---

    @Disabled("commit 5")
    @Test
    void shouldStripInlineComments() {
        // "@2 // addr" → instrução "@2"
    }

    @Disabled("commit 5")
    @Test
    void shouldSkipBlankLines() {
        // linhas vazias ignoradas
    }

    @Disabled("commit 5")
    @Test
    void shouldSkipFullLineComments() {
        // "// comentário" ignorado
    }

    // --- commit 6 (A) ---

    @Disabled("commit 6")
    @Test
    void shouldAdvanceThroughInstructions() {
        // advance() percorre N instruções
    }

    @Disabled("commit 6")
    @Test
    void shouldDetectEndOfFile() {
        // hasMoreInstructions() false no fim
    }

    // --- commit 7 (B) ---

    @Disabled("commit 7")
    @Test
    void shouldDetectAInstruction() {
        // "@10" → A_INSTRUCTION
    }

    @Disabled("commit 7")
    @Test
    void shouldDetectCInstruction() {
        // "D=M" → C_INSTRUCTION
    }

    @Disabled("commit 7")
    @Test
    void shouldDetectLabel() {
        // "(LOOP)" → LABEL
    }

    // --- commit 8 (B) ---

    @Disabled("commit 8")
    @Test
    void shouldExtractSymbolFromAInstruction() {
        // "@10" → "10", "@SCREEN" → "SCREEN"
    }

    @Disabled("commit 8")
    @Test
    void shouldExtractSymbolFromLabel() {
        // "(LOOP)" → "LOOP"
    }

    // --- commit 9 (B) ---

    @Disabled("commit 9")
    @Test
    void shouldParseDestComp() {
        // "D=M" → dest=D, comp=M, jump=""
    }

    @Disabled("commit 9")
    @Test
    void shouldParseCompJumpOnly() {
        // "D;JGT" → dest="", comp=D, jump=JGT
    }

    @Disabled("commit 9")
    @Test
    void shouldParseDestCompJump() {
        // "AMD=M+1;JMP"
    }
}
