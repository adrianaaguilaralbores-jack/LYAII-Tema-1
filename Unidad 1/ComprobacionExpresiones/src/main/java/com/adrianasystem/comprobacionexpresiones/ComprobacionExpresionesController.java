package com.adrianasystem.comprobacionexpresiones;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ComprobacionExpresionesController {

    // Variables declaradas
    private int entero = 10;       // variable entera
    private double decimal = 5.5;  // variable decimal
    private boolean bandera = true; // variable booleana

    // Elementos de la interfaz gráfica (FXML)
    @FXML
    private Label lblEntero, lblDecimal, lblBandera, lblResultado; // son etiquetas para mostrar valores y resultado

    @FXML
    private TextField txtExpresion; // campo de texto donde el usuario escribe la expresión

    @FXML
    public void initialize() {
        // Inicializa las etiquetas con los valores de las variables
        lblEntero.setText("int entero = " + entero);
        lblDecimal.setText("double decimal = " + decimal);
        lblBandera.setText("boolean bandera = " + bandera);
    }

    @FXML
    public void evaluarExpresion() {
        // Obtiene la expresión ingresada por el usuario en el campo de texto
        String expresion = txtExpresion.getText();
        // Llama al método que valida la expresión
        String resultado = comprobarExpresion(expresion);
        // Muestra el resultado en la etiqueta
        lblResultado.setText(resultado);
    }

    /**
     * Método que comprueba si la expresión escrita es aritmética o lógica,
     * y valida si se está utilizando correctamente según el tipo de variable.
     */
    private String comprobarExpresion(String expresion) {
        try {
            //  Operaciones aritméticas (+, -, *, /)
            if (expresion.contains("+") || expresion.contains("-") ||
                    expresion.contains("*") || expresion.contains("/")) {

                // Si la expresión incluye la variable booleana en una operación matemática sera error
                if (expresion.contains("bandera")) {
                    return "ERROR: No se pueden usar variables booleanas en operaciones aritméticas.";
                } else {
                    return "Expresión aritmética válida.";
                }

                //  Operaciones lógicas (&&, ||, !)
            } else if (expresion.contains("&&") || expresion.contains("||") || expresion.contains("!")) {

                // Si la expresión incluye variables numéricas en operaciones lógicas sera error
                if (expresion.contains("entero") || expresion.contains("decimal")) {
                    return "ERROR: Operadores lógicos solo aplican a booleanos.";
                } else {
                    return "Expresión lógica válida.";
                }

                //  No se reconoció la expresión como aritmética o lógica
            } else {
                return "No se pudo identificar la expresión. Revise la sintaxis.";
            }
        } catch (Exception e) {
            // Manejo de errores inesperados
            return "Error al evaluar la expresión: " + e.getMessage();
        }
    }
}
