module andisystem.arbol_de_expresiones {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens adrianaystem.arbol_de_expresiones to javafx.fxml;
    exports adrianaystem.arbol_de_expresiones;
}