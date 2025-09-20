module com.jesusystems.esquematraduccion {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.jesusystems.esquematraduccion to javafx.fxml;
    exports com.jesusystems.esquematraduccion;
}