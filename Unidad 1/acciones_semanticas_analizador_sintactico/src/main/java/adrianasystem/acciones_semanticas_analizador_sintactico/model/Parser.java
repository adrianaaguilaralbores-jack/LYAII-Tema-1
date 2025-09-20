package adrianasystem.acciones_semanticas_analizador_sintactico.model;



import adrianasystem.acciones_semanticas_analizador_sintactico.exceptions.SemanticException;
import java.util.List;

public class Parser {
    private List<Token> tokens;
    private SymbolTable symbolTable;
    private int currentScope;
    private int index;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.symbolTable = new SymbolTable();
        this.currentScope = 0;
        this.index = 0;
    }

    public void parse() {
        System.out.println("Iniciando análisis de " + tokens.size() + " tokens");

        while (index < tokens.size()) {
            Token token = tokens.get(index);
            System.out.println("Procesando token: " + token.getType() + " = " + token.getValue());

            if (token.getType().equals("KEYWORD") && token.getValue().equals("int")) {
                handleVariableDeclaration();
            } else if (token.getType().equals("IDENTIFIER")) {
                handleAssignment();
            } else {
                index++;
            }
        }
        System.out.println("Análisis completado. Símbolos encontrados: " + symbolTable.getSymbols().size());
    }

    private void handleVariableDeclaration() {
        Token typeToken = tokens.get(index++);

        if (index >= tokens.size() || !tokens.get(index).getType().equals("IDENTIFIER")) {
            throw new SemanticException("Se esperaba un identificador después de 'int'", typeToken.getLine());
        }

        Token idToken = tokens.get(index++);

        if (symbolTable.lookup(idToken.getValue(), currentScope) != null) {
            throw new SemanticException("Variable ya declarada: " + idToken.getValue(), idToken.getLine());
        }

        Symbol symbol = new Symbol(idToken.getValue(), typeToken.getValue(), null, currentScope);
        symbolTable.addSymbol(symbol);
        System.out.println("Variable declarada: " + idToken.getValue());

        if (index < tokens.size() && tokens.get(index).getValue().equals("=")) {
            index++;
            handleAssignmentValue(idToken.getValue());
        }
    }

    private void handleAssignment() {
        Token idToken = tokens.get(index++);

        Symbol symbol = symbolTable.lookup(idToken.getValue(), currentScope);
        if (symbol == null) {
            throw new SemanticException("Variable no declarada: " + idToken.getValue(), idToken.getLine());
        }

        if (index >= tokens.size() || !tokens.get(index).getValue().equals("=")) {
            throw new SemanticException("Se esperaba '=' después del identificador", idToken.getLine());
        }
        index++;

        handleAssignmentValue(idToken.getValue());
    }

    private void handleAssignmentValue(String varName) {
        if (index >= tokens.size()) {
            throw new SemanticException("Se esperaba un valor después de '='", tokens.get(index-1).getLine());
        }

        Token valueToken = tokens.get(index++);
        Symbol symbol = symbolTable.lookup(varName, currentScope);

        if (valueToken.getType().equals("NUMBER")) {
            symbol.setValue(Integer.parseInt(valueToken.getValue()));
            System.out.println("Asignado valor numérico: " + varName + " = " + valueToken.getValue());
        } else if (valueToken.getType().equals("IDENTIFIER")) {
            Symbol sourceSymbol = symbolTable.lookup(valueToken.getValue(), currentScope);
            if (sourceSymbol == null) {
                throw new SemanticException("Variable no declarada: " + valueToken.getValue(), valueToken.getLine());
            }
            symbol.setValue(sourceSymbol.getValue());
            System.out.println("Asignado valor de variable: " + varName + " = " + sourceSymbol.getValue());
        } else {
            throw new SemanticException("Tipo de valor no válido", valueToken.getLine());
        }
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}