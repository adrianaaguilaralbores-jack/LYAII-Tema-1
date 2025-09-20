module com.jesusystems.tabladesombolos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.adrianasystems.tabladesombolos to javafx.fxml;
    exports com.adrianasystems.tabladesombolos;
}