module andisystem.acciones_semanticas_analizador_sintactico {
    requires javafx.controls;
    requires javafx.fxml;

    opens adrianasystem.acciones_semanticas_analizador_sintactico to javafx.fxml;

    opens adrianasystem.acciones_semanticas_analizador_sintactico.model to javafx.base;

    exports adrianasystem.acciones_semanticas_analizador_sintactico;
    exports adrianasystem.acciones_semanticas_analizador_sintactico.model;
    exports adrianasystem.acciones_semanticas_analizador_sintactico.exceptions;
}