package com.adrianasystems.pilasemantica;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Stack;

public class PilaSemanticaController {

    static class Nodo {
        String tipo;  // Tipo de dato (int en este caso)
        int valor;

        Nodo(int valor) {
            this.tipo = "int";
            this.valor = valor;
        }
    }

    @FXML
    private TextField inputExpresion;

    @FXML
    private Button btnProcesar; // boton para procesar la expresion

    @FXML
    private TextArea txtResultado; // es un espacio donde mostrara el resultado

    @FXML
    private void initialize() {
        btnProcesar.setOnAction(e -> procesarExpresion());
    }

    private void procesarExpresion() {
        String expr = inputExpresion.getText().replaceAll("\\s+", "");
        Stack<Nodo> pilaSemantica = new Stack<>();
        char operador = '+';
        String numeroActual = "";

        txtResultado.clear();
        txtResultado.appendText("Expresión ingresada: " + expr + "\n\n");

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                numeroActual += c;
            } else if (c == '+' || c == '-' || c == '*') {
                // Cerrar el número que veníamos leyendo aplicando el operador ANTERIOR
                int valor = Integer.parseInt(numeroActual);

                if (operador == '+') {
                    pilaSemantica.push(new Nodo(valor));
                    txtResultado.appendText("Push +" + valor + "\n");
                } else if (operador == '-') {
                    pilaSemantica.push(new Nodo(-valor));
                    txtResultado.appendText("Push -" + valor + "\n");
                } else if (operador == '*') {
                    // Multiplicar con el tope de la pila
                    Nodo anterior = pilaSemantica.pop();
                    int producto = anterior.valor * valor;
                    pilaSemantica.push(new Nodo(producto));
                    txtResultado.appendText("Multiplico " + anterior.valor + " * " + valor + " = " + producto + "\n");
                }

                operador = c;       // aqui se observa que el operador actual pasa a ser el "anterior" del siguiente número
                numeroActual = "";  // se reinicia la acumulación de dígitos
            }
        }

        // aqui se procesa el último número pendiente al final de la expresión
        if (!numeroActual.isEmpty()) {
            int valor = Integer.parseInt(numeroActual);

            if (operador == '+') {
                pilaSemantica.push(new Nodo(valor));
                txtResultado.appendText("Push +" + valor + "\n");
            } else if (operador == '-') {
                pilaSemantica.push(new Nodo(-valor));
                txtResultado.appendText("Push -" + valor + "\n");
            } else if (operador == '*') {
                Nodo anterior = pilaSemantica.pop();
                int producto = anterior.valor * valor;
                pilaSemantica.push(new Nodo(producto));
                txtResultado.appendText("Multiplico " + anterior.valor + " * " + valor + " = " + producto + "\n");
            }
        }

        int resultado = 0;
        while (!pilaSemantica.isEmpty()) {
            Nodo nodo = pilaSemantica.pop();
            resultado += nodo.valor;
            txtResultado.appendText("Se procesa nodo con valor: " + nodo.valor +
                    ", resultado parcial: " + resultado + "\n");
        }

        txtResultado.appendText("\nResultado final: " + resultado);
    }
}
