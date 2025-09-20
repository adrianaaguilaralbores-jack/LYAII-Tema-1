package adrianaystem.arbol_de_expresiones;


import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TreeVisualization extends Pane {
    private static final double HORIZONTAL_SPACING = 50;
    private static final double VERTICAL_SPACING = 80;
    private static final double NODE_RADIUS = 20;

    public void drawTree(ExpressionTree.Node node) {
        getChildren().clear();
        if (node != null) {
            drawTreeRecursive(node, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTreeRecursive(ExpressionTree.Node node, double x, double y, double hOffset) {
        // Dibujar el nodo
        Text text = new Text(x - 10, y + 5, node.value);
        text.setFont(Font.font(16));
        text.setFill(Color.WHITE);

        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(x, y, NODE_RADIUS);
        circle.setFill(Color.DARKBLUE);
        circle.setStroke(Color.BLACK);

        getChildren().addAll(circle, text);

        // Dibujar conexiones con hijos
        if (node.left != null) {
            double childX = x - hOffset;
            double childY = y + VERTICAL_SPACING;
            drawConnection(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            drawTreeRecursive(node.left, childX, childY, hOffset / 2);
        }

        if (node.right != null) {
            double childX = x + hOffset;
            double childY = y + VERTICAL_SPACING;
            drawConnection(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            drawTreeRecursive(node.right, childX, childY, hOffset / 2);
        }
    }

    private void drawConnection(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        getChildren().add(line);
    }
}