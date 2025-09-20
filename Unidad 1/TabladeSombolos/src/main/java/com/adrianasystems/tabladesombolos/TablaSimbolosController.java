package com.adrianasystems.tabladesombolos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TablaSimbolosController {

    @FXML
    private TextField inputCodigo;

    @FXML
    private Button btnTraducir;

    @FXML
    private TextArea outputResultado;

    @FXML
    private TableView<Simbolo> tablaSimbolos;

    @FXML
    private TableColumn<Simbolo, String> colNombre;
    @FXML
    private TableColumn<Simbolo, String> colTipo;
    @FXML
    private TableColumn<Simbolo, String> colValor;

    @FXML
    private TableView<Direccion> tablaDirecciones;

    @FXML
    private TableColumn<Direccion, String> colNombreDir;
    @FXML
    private TableColumn<Direccion, String> colDireccion;

    private ObservableList<Simbolo> listaSimbolos = FXCollections.observableArrayList();
    private ObservableList<Direccion> listaDirecciones = FXCollections.observableArrayList();

    private int siguienteDireccion = 1000;

    @FXML
    private void initialize() {
        // Configurar columnas
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colTipo.setCellValueFactory(data -> data.getValue().tipoProperty());
        colValor.setCellValueFactory(data -> data.getValue().valorProperty());

        colNombreDir.setCellValueFactory(data -> data.getValue().nombreProperty());
        colDireccion.setCellValueFactory(data -> data.getValue().direccionProperty());

        tablaSimbolos.setItems(listaSimbolos);
        tablaDirecciones.setItems(listaDirecciones);

        btnTraducir.setOnAction(e -> agregarCodigo());
    }

    private void agregarCodigo() {
        String codigo = inputCodigo.getText().trim();
        if (codigo.isEmpty()) {
            outputResultado.setText("No se ingresó ningún código.");
            return;
        }

        String nombre = "Desconocido";
        String tipo = "Desconocido";
        String valor = codigo;

        // Expresión para detectar: tipo nombre = valor;
        String regex = "(int|double|String|float|char|boolean)\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\s*=\\s*(.+);?";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(codigo);

        if (matcher.matches()) {
            tipo = matcher.group(1);
            nombre = matcher.group(2);
            valor = matcher.group(3);
        }

        Simbolo simbolo = new Simbolo(nombre, tipo, valor);
        listaSimbolos.add(simbolo);

        Direccion direccion = new Direccion(nombre, String.valueOf(siguienteDireccion));
        listaDirecciones.add(direccion);
        siguienteDireccion++;

        outputResultado.setText("Código agregado correctamente:\n" + codigo);
        inputCodigo.clear();
    }
}
