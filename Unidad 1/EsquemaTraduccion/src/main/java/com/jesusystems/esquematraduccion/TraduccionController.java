package com.jesusystems.esquematraduccion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraduccionController {

    @FXML
    private TextField inputCodigo;

    @FXML
    private Button btnTraducir;

    @FXML
    private TextArea outputResultado;

    @FXML
    private void initialize() {
        btnTraducir.setOnAction(e -> traducir());
    }

    private void traducir() {
        String instruccion = inputCodigo.getText().trim();

        // Regex generales para declaraciones
        String regexInt = "int\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*(\\d+)\\s*;";
        String regexDouble = "double\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*(\\d+(?:\\.\\d+)?)\\s*;";
        String regexString = "String\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*\"([^\"]*)\"\\s*;";

        if (instruccion.matches(regexInt)) {
            Matcher m = Pattern.compile(regexInt).matcher(instruccion);
            m.find();
            String id = m.group(1);
            String valor = m.group(2);
            outputResultado.setText("✅ Traducción exitosa\n" +
                    "Tipo: int\nIdentificador: " + id + "\nValor: " + valor +
                    "\nCódigo intermedio: [DECLARAR INT] " + id + " = " + valor);
        } else if (instruccion.matches(regexDouble)) {
            Matcher m = Pattern.compile(regexDouble).matcher(instruccion);
            m.find();
            String id = m.group(1);
            String valor = m.group(2);
            outputResultado.setText("✅ Traducción exitosa\n" +
                    "Tipo: double\nIdentificador: " + id + "\nValor: " + valor +
                    "\nCódigo intermedio: [DECLARAR DOUBLE] " + id + " = " + valor);
        } else if (instruccion.matches(regexString)) {
            Matcher m = Pattern.compile(regexString).matcher(instruccion);
            m.find();
            String id = m.group(1);
            String valor = m.group(2);
            outputResultado.setText("✅ Traducción exitosa\n" +
                    "Tipo: String\nIdentificador: " + id + "\nValor: " + valor +
                    "\nCódigo intermedio: [DECLARAR STRING] " + id + " = \"" + valor + "\"");
        } else {
            outputResultado.setText("❌ Error: Instrucción no reconocida\n" +
                    "Ejemplos válidos:\n" +
                    "   int x = 10;\n" +
                    "   double y = 3.14;\n" +
                    "   String nombre = \"Juan\";");
        }
    }
}
