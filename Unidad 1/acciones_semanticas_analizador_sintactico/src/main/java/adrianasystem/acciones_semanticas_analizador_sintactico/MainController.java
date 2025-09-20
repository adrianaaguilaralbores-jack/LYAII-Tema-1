package adrianasystem.acciones_semanticas_analizador_sintactico;


import adrianasystem.acciones_semanticas_analizador_sintactico.model.Symbol;
import adrianasystem.acciones_semanticas_analizador_sintactico.model.Token;
import adrianasystem.acciones_semanticas_analizador_sintactico.model.Parser;
import adrianasystem.acciones_semanticas_analizador_sintactico.exceptions.SemanticException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML private TextArea codeInput;
    @FXML private TableView<Symbol> symbolTable;
    @FXML private ListView<String> errorList;
    @FXML private TableColumn<Symbol, String> colName;
    @FXML private TableColumn<Symbol, String> colType;
    @FXML private TableColumn<Symbol, String> colValue;
    @FXML private TableColumn<Symbol, Integer> colScope;

    private ObservableList<Symbol> symbols = FXCollections.observableArrayList();
    private ObservableList<String> errors = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla con lambdas
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colValue.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().getValue();
            return new SimpleStringProperty(value != null ? value.toString() : "null");
        });
        colScope.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getScope()).asObject());

        symbolTable.setItems(symbols);
        errorList.setItems(errors);

        // Agregar datos de ejemplo para prueba
        agregarDatosEjemplo();
    }

    @FXML
    private void analyzeCode() {
        errors.clear();
        symbols.clear();

        String code = codeInput.getText();
        if (code == null || code.trim().isEmpty()) {
            errors.add("Error: No hay código para analizar");
            return;
        }

        List<Token> tokens = tokenize(code);
        System.out.println("Tokens encontrados: " + tokens.size());

        for (Token token : tokens) {
            System.out.println("Token: " + token.getType() + " = " + token.getValue() + " (línea " + token.getLine() + ")");
        }

        try {
            Parser parser = new Parser(tokens);
            parser.parse();
            symbols.addAll(parser.getSymbolTable().getSymbols());
            System.out.println("Símbolos encontrados: " + symbols.size());
        } catch (SemanticException e) {
            errors.add("Error semántico en línea " + e.getLine() + ": " + e.getMessage());
        } catch (Exception e) {
            errors.add("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        String[] lines = code.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] words = line.split("\\s+");
            for (String word : words) {
                if (word.equals("int")) {
                    tokens.add(new Token("KEYWORD", word, i+1));
                } else if (word.equals("=")) {
                    tokens.add(new Token("OPERATOR", word, i+1));
                } else if (word.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                    tokens.add(new Token("IDENTIFIER", word, i+1));
                } else if (word.matches("\\d+")) {
                    tokens.add(new Token("NUMBER", word, i+1));
                } else if (word.equals(";")) {
                    // Ignorar punto y coma para este ejemplo
                } else {
                    tokens.add(new Token("UNKNOWN", word, i+1));
                }
            }
        }
        return tokens;
    }

    private void agregarDatosEjemplo() {
        // Agregar algunos datos de ejemplo para verificar que la tabla funciona
        symbols.add(new Symbol("ejemplo", "int", 42, 0));
        symbols.add(new Symbol("variable", "string", "hola", 1));
    }
}