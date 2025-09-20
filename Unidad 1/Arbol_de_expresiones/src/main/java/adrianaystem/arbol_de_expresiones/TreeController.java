package adrianaystem.arbol_de_expresiones;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TreeController {
    @FXML private TextField expressionInput;
    @FXML private TreeVisualization treeVisualization;
    @FXML private Label resultText;
    @FXML private Label infixText;
    @FXML private Label prefixText;
    @FXML private Label postfixText;

    private ExpressionTree expressionTree = new ExpressionTree();

    @FXML
    private void initialize() {
        // Inicializar con un ejemplo
        expressionInput.setText("3 4 + 5 *");
        handleBuildTree();
    }

    @FXML
    private void handleBuildTree() {
        try {
            String postfixExpr = expressionInput.getText().trim();
            ExpressionTree.Node root = expressionTree.buildTreeFromPostfix(postfixExpr);
            treeVisualization.drawTree(root);

            double result = expressionTree.evaluate(root);
            resultText.setText("Resultado: " + result);

            infixText.setText("Notación Infija: " + expressionTree.toInfix(root));
            prefixText.setText("Notación Prefija: " + expressionTree.toPrefix(root));
            postfixText.setText("Notación Posfija: " + expressionTree.toPostfix(root));

        } catch (Exception e) {
            showError("Error al procesar la expresión", e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        expressionInput.clear();
        treeVisualization.getChildren().clear();
        resultText.setText("Resultado: ");
        infixText.setText("Notación Infija: ");
        prefixText.setText("Notación Prefija: ");
        postfixText.setText("Notación Posfija: ");
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}