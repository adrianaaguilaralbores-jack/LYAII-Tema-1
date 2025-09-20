package adrianasystem.acciones_semanticas_analizador_sintactico.model;


public class Token {
    private String type;
    private String value;
    private int line;

    public Token(String type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    // Getters
    public String getType() { return type; }
    public String getValue() { return value; }
    public int getLine() { return line; }
}
