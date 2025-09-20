package adrianasystem.acciones_semanticas_analizador_sintactico.exceptions;



public class SemanticException extends RuntimeException {
    private int line;

    public SemanticException(String message, int line) {
        super(message);
        this.line = line;
    }

    public int getLine() {
        return line;
    }
}