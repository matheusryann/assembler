package symboltable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testes unitários — um método por commit (ver docs/CHECKLIST.md).
 */
class SymbolTableTest {

    private SymbolTable table;

    @BeforeEach
    void setUp() {
        table = new SymbolTable();
    }

    // --- commit 2 (B) ---

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

    // --- commit 3 (B) ---

    @Disabled("commit 3")
    @Test
    void shouldAddLabelEntry() {
        // addEntry("LOOP", 6) → getAddress("LOOP") == 6
    }

    @Disabled("commit 3")
    @Test
    void shouldReturnNullForUnknownSymbol() {
        assertNull(table.getAddress("INEXISTENTE"));
    }

    // --- commit 4 (A) ---

    @Disabled("commit 4")
    @Test
    void shouldAllocateVariablesStartingAt16() {
        // addVariable("i") → 16, addVariable("sum") → 17
    }

    @Disabled("commit 4")
    @Test
    void shouldReuseExistingVariable() {
        // addVariable("i") duas vezes → mesmo endereço
    }

    @Disabled("commit 4")
    @Test
    void shouldReportContains() {
        // contains("LOOP") após addEntry
    }
}
