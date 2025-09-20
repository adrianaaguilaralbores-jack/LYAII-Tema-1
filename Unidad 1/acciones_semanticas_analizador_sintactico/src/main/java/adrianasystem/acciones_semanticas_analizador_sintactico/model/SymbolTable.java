package adrianasystem.acciones_semanticas_analizador_sintactico.model;


import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    private List<Symbol> symbols;

    public SymbolTable() {
        this.symbols = new ArrayList<>();
    }

    public void addSymbol(Symbol symbol) {
        symbols.add(symbol);
    }

    public Symbol lookup(String name, int scope) {
        return symbols.stream()
                .filter(s -> s.getName().equals(name) && s.getScope() == scope)
                .findFirst()
                .orElse(null);
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }
}
