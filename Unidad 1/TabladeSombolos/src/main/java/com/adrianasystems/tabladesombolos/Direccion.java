package com.adrianasystems.tabladesombolos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Direccion {
    private final StringProperty nombre;
    private final StringProperty direccion;

    public Direccion(String nombre, String direccion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.direccion = new SimpleStringProperty(direccion);
    }

    public StringProperty nombreProperty() { return nombre; }
    public StringProperty direccionProperty() { return direccion; }
}
