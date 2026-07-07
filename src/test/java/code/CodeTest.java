package code;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários — um método por commit (ver docs/CHECKLIST.md).
 */
class CodeTest {

    // --- commit 10 (A) ---

    @Test
    void shouldEncodeCompZero() {
        assertEquals("0101010", Code.comp("0"));
        assertEquals("0111111", Code.comp("1"));
        assertEquals("0111010", Code.comp("-1"));
    }

    @Test
    void shouldEncodeCompWithM() {
        assertEquals("1110000", Code.comp("M"));
        assertEquals("1000010", Code.comp("D+M"));
        assertEquals("1010011", Code.comp("D-M"));
        assertEquals("1000000", Code.comp("D&M"));
        assertEquals("1010101", Code.comp("D|M"));
    }

    @Test
    void shouldEncodeCompArithmetic() {
        assertEquals("0000010", Code.comp("D+A"));
        assertEquals("0010011", Code.comp("D-A"));
        assertEquals("0010101", Code.comp("D|A"));
        assertEquals("0000000", Code.comp("D&A"));
        assertEquals("0011111", Code.comp("D+1"));
        assertEquals("0001110", Code.comp("D-1"));
        assertEquals("0001101", Code.comp("!D"));
    }

    // --- commit 11 (A) ---

    @Disabled("commit 11")
    @Test
    void shouldEncodeDest() {
        // dest("D") → "010", dest("AMD") → "111"
    }

    @Disabled("commit 11")
    @Test
    void shouldEncodeEmptyDest() {
        // dest("") → "000"
    }

    @Disabled("commit 11")
    @Test
    void shouldEncodeJump() {
        // jump("JGT") → "001", jump("JMP") → "111", jump("") → "000"
    }

    // --- commit 12 (B) ---

    @Disabled("commit 12")
    @Test
    void shouldEncodeNumericAInstruction() {
        // encodeAInstruction("10", table) → "0000000000001010"
    }

    @Disabled("commit 12")
    @Test
    void shouldPadTo16Bits() {
        // sempre 16 caracteres, bit 15 = '0'
    }

    // --- commit 13 (B) ---

    @Disabled("commit 13")
    @Test
    void shouldEncodePredefinedSymbol() {
        // @SCREEN → 0100000000000000
    }

    @Disabled("commit 13")
    @Test
    void shouldEncodeLabel() {
        // label LOOP=6 → 0000000000000110
    }

    @Disabled("commit 13")
    @Test
    void shouldEncodeNewVariable() {
        // variável nova → RAM 16+
    }

    // --- commit 14 (A) ---

    @Disabled("commit 14")
    @Test
    void shouldEncodeDM() {
        // D=M → 1111110000010000
    }

    @Disabled("commit 14")
    @Test
    void shouldEncodeMD() {
        // M=D → 1110001100001000
    }

    @Disabled("commit 14")
    @Test
    void shouldEncodeDJump() {
        // D;JGT → 1110001100000001
    }
}
