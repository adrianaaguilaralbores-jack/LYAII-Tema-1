package com.adrianasystems.tabladesombolos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Simbolo {
    private final StringProperty nombre;
    private final StringProperty tipo;
    private final StringProperty valor;

    public Simbolo(String nombre, String tipo, String valor) {
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.valor = new SimpleStringProperty(valor);
    }

    public StringProperty nombreProperty() { return nombre; }
    public StringProperty tipoProperty() { return tipo; }
    public StringProperty valorProperty() { return valor; }
}
