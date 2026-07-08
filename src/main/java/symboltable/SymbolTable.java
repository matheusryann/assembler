package symboltable;

import java.util.HashMap;
import java.util.Map;

/**
 * Tabela de símbolos: rótulos, variáveis e predefinidos.
 *
 */
public class SymbolTable {

    private final Map<String, Integer> symbols = new HashMap<>();
    private int nextVariableAddress = 16;

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

    public boolean contains(String symbol) {
        return symbols.containsKey(symbol);
    }

    public Integer getAddress(String symbol) {
        return symbols.get(symbol);
    }

    /**
     * Aloca endereço de RAM para variável user-defined (16, 17, 18…).
     * Se o símbolo já existir, devolve o endereço existente.
     */
    public int addVariable(String symbol) {
        if (symbols.containsKey(symbol)) {
            return symbols.get(symbol);
        }
        int address = nextVariableAddress;
        symbols.put(symbol, address);
        nextVariableAddress++;
        return address;
    }
}
