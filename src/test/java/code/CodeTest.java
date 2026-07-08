package code;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testes unitários do encoding Hack.
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

    @Test
    void shouldEncodeDest() {
        assertEquals("010", Code.dest("D"));
        assertEquals("111", Code.dest("AMD"));
        assertEquals("001", Code.dest("M"));
        assertEquals("110", Code.dest("AD"));
    }

    @Test
    void shouldEncodeEmptyDest() {
        assertEquals("000", Code.dest(""));
    }

    @Test
    void shouldEncodeJump() {
        assertEquals("001", Code.jump("JGT"));
        assertEquals("111", Code.jump("JMP"));
        assertEquals("000", Code.jump(""));
        assertEquals("010", Code.jump("JEQ"));
    }

    // --- commit 12 (B) ---

    @Test
    void shouldEncodeNumericAInstruction() {
        symboltable.SymbolTable table = new symboltable.SymbolTable();
        assertEquals("0000000000001010", Code.encodeAInstruction("10", table));
    }
    @Test
    void shouldPadTo16Bits() {
        symboltable.SymbolTable table = new symboltable.SymbolTable();
        String bits = Code.encodeAInstruction("2", table);
        assertEquals(16, bits.length());
        assertEquals('0', bits.charAt(0));
    }

    // --- commit 13 (B) ---

    @Test
    void shouldEncodePredefinedSymbol() {
        symboltable.SymbolTable table = new symboltable.SymbolTable();
        assertEquals("0100000000000000", Code.encodeAInstruction("SCREEN", table));
    }
    @Test
    void shouldEncodeLabel() {
        symboltable.SymbolTable table = new symboltable.SymbolTable();
        table.addEntry("LOOP", 6);
        assertEquals("0000000000000110", Code.encodeAInstruction("LOOP", table));
    }
    @Test
    void shouldEncodeNewVariable() {
        symboltable.SymbolTable table = new symboltable.SymbolTable();
        assertEquals("0000000000010000", Code.encodeAInstruction("i", table));
        // chamada subsequente deve reutilizar o mesmo endereço
        assertEquals("0000000000010000", Code.encodeAInstruction("i", table));
    }

    // --- commit 14 (A) ---

    @Test
    void shouldEncodeDM() {
        assertEquals("1111110000010000", Code.encodeCInstruction("D", "M", ""));
    }

    @Test
    void shouldEncodeMD() {
        assertEquals("1110001100001000", Code.encodeCInstruction("M", "D", ""));
    }

    @Test
    void shouldEncodeDJump() {
        assertEquals("1110001100000001", Code.encodeCInstruction("", "D", "JGT"));
    }
}
