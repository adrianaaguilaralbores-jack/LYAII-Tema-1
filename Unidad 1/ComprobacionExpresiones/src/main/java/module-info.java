module com.example.comprobacionexpresiones {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.adrianasystem.comprobacionexpresiones to javafx.fxml;
    exports com.adrianasystem.comprobacionexpresiones;
}