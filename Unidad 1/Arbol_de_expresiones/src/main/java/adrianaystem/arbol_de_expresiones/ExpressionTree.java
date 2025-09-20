package adrianaystem.arbol_de_expresiones;

import java.util.Stack;

public class ExpressionTree {

    public static class Node {
        String value;
        Node left, right;

        Node(String value) {
            this.value = value;
            this.left = this.right = null;
        }

        Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private Node root;

    public ExpressionTree() {
        root = null;
    }

    public Node buildTreeFromPostfix(String postfixExpr) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = postfixExpr.split(" ");

        for (String token : tokens) {
            token = token.trim();
            if (token.isEmpty()) continue;

            if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Expresión posfija inválida: operador sin operandos suficientes");
                }
                Node right = stack.pop();
                Node left = stack.pop();
                Node operatorNode = new Node(token, left, right);
                stack.push(operatorNode);
            } else {
                stack.push(new Node(token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expresión posfija inválida: demasiados operandos");
        }

        root = stack.pop();
        return root;
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    public double evaluate(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            try {
                return Double.parseDouble(node.value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Operando inválido: " + node.value);
            }
        }

        double leftVal = evaluate(node.left);
        double rightVal = evaluate(node.right);

        return switch (node.value) {
            case "+" -> leftVal + rightVal;
            case "-" -> leftVal - rightVal;
            case "*" -> leftVal * rightVal;
            case "/" -> {
                if (rightVal == 0) throw new ArithmeticException("División por cero");
                yield leftVal / rightVal;
            }
            case "^" -> Math.pow(leftVal, rightVal);
            default -> throw new IllegalArgumentException("Operador desconocido: " + node.value);
        };
    }

    public Node getRoot() {
        return root;
    }

    public String toInfix(Node node) {
        if (node == null) return "";

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return "(" + toInfix(node.left) + " " + node.value + " " + toInfix(node.right) + ")";
    }

    public String toPrefix(Node node) {
        if (node == null) return "";

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return node.value + " " + toPrefix(node.left) + " " + toPrefix(node.right);
    }

    public String toPostfix(Node node) {
        if (node == null) return "";

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return toPostfix(node.left) + " " + toPostfix(node.right) + " " + node.value;
    }
}