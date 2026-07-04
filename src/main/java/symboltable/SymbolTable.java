package symboltable;

import java.util.HashMap;
import java.util.Map;

/**
 * Tabela de símbolos: rótulos, variáveis e predefinidos.
 *
 * @see docs/GUIA-MONTADOR.md
 */
public class SymbolTable {

    private final Map<String, Integer> symbols = new HashMap<>();

    public SymbolTable() {
        for (int i = 0; i <= 15; i++) {
            symbols.put("R" + i, i);
        }
        symbols.put("SP", 0);
        symbols.put("LCL", 1);
        symbols.put("ARG", 2);
        symbols.put("THIS", 3);
        symbols.put("THAT", 4);
        symbols.put("SCREEN", 16384);
        symbols.put("KBD", 24576);
    }

    public void addEntry(String symbol, int address) {
        symbols.put(symbol, address);
    }

    // TODO: commit 4
    public boolean contains(String symbol) {
        throw new UnsupportedOperationException("TODO: implementar contains (commit 4)");
    }

    public Integer getAddress(String symbol) {
        return symbols.get(symbol);
    }

    // TODO: commit 4
    public int addVariable(String symbol) {
        throw new UnsupportedOperationException("TODO: implementar addVariable (commit 4)");
    }
}
