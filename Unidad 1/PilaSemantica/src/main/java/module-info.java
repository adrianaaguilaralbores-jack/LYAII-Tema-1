module com.example.pilasemantica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.adrianasystems.pilasemantica to javafx.fxml;
    exports com.adrianasystems.pilasemantica;
}