package parser;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes unitários — um método por commit (ver docs/CHECKLIST.md).
 */
class ParserTest {

    // --- commit 5 (A) ---

    @Test
    void shouldStripInlineComments(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("inline.asm");
        Files.writeString(asm, "@2 // addr\n");

        Parser parser = new Parser(asm);
        assertTrue(parser.hasMoreInstructions());
        parser.advance();
        assertEquals("2", parser.symbol());
        assertFalse(parser.hasMoreInstructions());
    }

    @Test
    void shouldSkipBlankLines(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("blank.asm");
        Files.writeString(asm, "@0\n\n   \nD=M\n");

        Parser parser = new Parser(asm);
        parser.advance();
        assertEquals("0", parser.symbol());
        parser.advance();
        assertFalse(parser.hasMoreInstructions());
    }

    @Test
    void shouldSkipFullLineComments(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("comments.asm");
        Files.writeString(asm, "// comentário\n// outro\n");

        Parser parser = new Parser(asm);
        assertFalse(parser.hasMoreInstructions());
    }

    // --- commit 6 (A) ---

    @Test
    void shouldAdvanceThroughInstructions(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("program.asm");
        Files.writeString(asm, "@0\nD=M\n@1\nD=D+M\n@2\nM=D\n");

        Parser parser = new Parser(asm);
        int count = 0;
        while (parser.hasMoreInstructions()) {
            parser.advance();
            count++;
        }
        assertEquals(6, count);
    }

    @Test
    void shouldDetectEndOfFile(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("single.asm");
        Files.writeString(asm, "D=M\n");

        Parser parser = new Parser(asm);
        assertTrue(parser.hasMoreInstructions());
        parser.advance();
        assertFalse(parser.hasMoreInstructions());
    }

    // --- commit 7 (B) ---

    @Test
    void shouldDetectAInstruction(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("a.asm");
        Files.writeString(asm, "@10\n");

        Parser parser = new Parser(asm);
        parser.advance();
        assertEquals(Parser.InstructionType.A_INSTRUCTION, parser.instructionType());
    }

    @Test
    void shouldDetectCInstruction(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("c.asm");
        Files.writeString(asm, "D=M\n");

        Parser parser = new Parser(asm);
        parser.advance();
        assertEquals(Parser.InstructionType.C_INSTRUCTION, parser.instructionType());
    }

    @Test
    void shouldDetectLabel(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("label.asm");
        Files.writeString(asm, "(LOOP)\n");

        Parser parser = new Parser(asm);
        parser.advance();
        assertEquals(Parser.InstructionType.LABEL, parser.instructionType());
    }

    // --- commit 8 (B) ---

    @Test
    void shouldExtractSymbolFromAInstruction(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("symbols.asm");
        Files.writeString(asm, "@10\n@SCREEN\n");

        Parser parser = new Parser(asm);
        parser.advance();
        assertEquals("10", parser.symbol());
        parser.advance();
        assertEquals("SCREEN", parser.symbol());
    }

    @Test
    void shouldExtractSymbolFromLabel(@TempDir Path tempDir) throws IOException {
        Path asm = tempDir.resolve("label.asm");
        Files.writeString(asm, "(LOOP)\n");

        Parser parser = new Parser(asm);
        parser.advance();
        assertEquals("LOOP", parser.symbol());
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
