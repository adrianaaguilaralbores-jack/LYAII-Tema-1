package adrianasystems.manejoerroressemanticos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoEdad;

    @FXML
    private TextField campoEmail;

    @FXML
    private Label etiquetaError;

    @FXML
    private void initialize() {
        // Inicialización si es necesaria
    }

    @FXML
    private void validarDatos() {
        try {
            // Limpiar mensajes anteriores
            etiquetaError.setText("");

            // Validar nombre
            validarNombre(campoNombre.getText());

            // Validar edad
            validarEdad(campoEdad.getText());

            // Validar email
            validarEmail(campoEmail.getText());

            // Si todo está correcto
            mostrarAlerta(Alert.AlertType.INFORMATION, "Validación Exitosa",
                    "Todos los datos son válidos.");

        } catch (ErrorSemanticoException e) {
            etiquetaError.setText(e.getMessage());
        } catch (Exception e) {
            etiquetaError.setText("Error inesperado: " + e.getMessage());
        }
    }

    private void validarNombre(String nombre) throws ErrorSemanticoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorSemanticoException("El nombre no puede estar vacío.");
        }

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            throw new ErrorSemanticoException("El nombre solo puede contener letras y espacios.");
        }

        if (nombre.length() < 3) {
            throw new ErrorSemanticoException("El nombre debe tener al menos 3 caracteres.");
        }
    }

    private void validarEdad(String edadTexto) throws ErrorSemanticoException {
        if (edadTexto == null || edadTexto.trim().isEmpty()) {
            throw new ErrorSemanticoException("La edad no puede estar vacía.");
        }

        try {
            int edad = Integer.parseInt(edadTexto);

            if (edad < 0 || edad > 120) {
                throw new ErrorSemanticoException("La edad debe estar entre 0 y 120 años.");
            }

            if (edad < 18) {
                throw new ErrorSemanticoException("Debe ser mayor de edad (18+).");
            }

        } catch (NumberFormatException e) {
            throw new ErrorSemanticoException("La edad debe ser un número válido.");
        }
    }

    private void validarEmail(String email) throws ErrorSemanticoException {
        if (email == null || email.trim().isEmpty()) {
            throw new ErrorSemanticoException("El email no puede estar vacío.");
        }

        // Expresión regular simple para validar email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {
            throw new ErrorSemanticoException("El formato del email no es válido.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}