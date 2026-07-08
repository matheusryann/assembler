package symboltable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes unitários da tabela de símbolos.
 */
class SymbolTableTest {

    private SymbolTable table;

    @BeforeEach
    void setUp() {
        table = new SymbolTable();
    }

    // --- commit 2 ---

    @Test
    void shouldInitializeRegisters() {
        assertEquals(0, table.getAddress("R0"));
        assertEquals(1, table.getAddress("R1"));
        assertEquals(15, table.getAddress("R15"));
    }

    @Test
    void shouldInitializeVmPointers() {
        assertEquals(0, table.getAddress("SP"));
        assertEquals(1, table.getAddress("LCL"));
        assertEquals(2, table.getAddress("ARG"));
        assertEquals(3, table.getAddress("THIS"));
        assertEquals(4, table.getAddress("THAT"));
    }

    @Test
    void shouldInitializeIoSymbols() {
        assertEquals(16384, table.getAddress("SCREEN"));
        assertEquals(24576, table.getAddress("KBD"));
    }

    // --- commit 3 ---

    @Test
    void shouldAddLabelEntry() {
        table.addEntry("LOOP", 6);
        assertEquals(6, table.getAddress("LOOP"));

        table.addEntry("END", 20);
        assertEquals(20, table.getAddress("END"));
    }

    @Test
    void shouldReturnNullForUnknownSymbol() {
        assertNull(table.getAddress("INEXISTENTE"));
    }

    @Test
    void shouldOverwriteLabelEntry() {
        table.addEntry("LOOP", 6);
        table.addEntry("LOOP", 10);
        assertEquals(10, table.getAddress("LOOP"));
    }

    // --- commit 4 ---

    @Test
    void shouldAllocateVariablesStartingAt16() {
        assertEquals(16, table.addVariable("i"));
        assertEquals(17, table.addVariable("sum"));
        assertEquals(18, table.addVariable("counter"));

        assertEquals(16, table.getAddress("i"));
        assertEquals(17, table.getAddress("sum"));
    }

    @Test
    void shouldReuseExistingVariable() {
        assertEquals(16, table.addVariable("i"));
        assertEquals(16, table.addVariable("i"));
        assertEquals(17, table.addVariable("sum"));
    }

    @Test
    void shouldReportContains() {
        assertFalse(table.contains("LOOP"));
        assertTrue(table.contains("R0"));
        assertTrue(table.contains("SCREEN"));

        table.addEntry("LOOP", 6);
        assertTrue(table.contains("LOOP"));

        table.addVariable("i");
        assertTrue(table.contains("i"));
        assertFalse(table.contains("naoExiste"));
    }
}
