package adrianasystem.acciones_semanticas_analizador_sintactico.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Symbol {
    private SimpleStringProperty name;
    private SimpleStringProperty type;
    private SimpleObjectProperty<Object> value;
    private SimpleIntegerProperty scope;

    public Symbol(String name, String type, Object value, int scope) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.value = new SimpleObjectProperty<>(value);
        this.scope = new SimpleIntegerProperty(scope);
    }

    // Getters estándar para PropertyValueFactory
    public String getName() { return name.get(); }
    public String getType() { return type.get(); }
    public Object getValue() { return value.get(); }
    public int getScope() { return scope.get(); }

    // Properties para posible uso avanzado
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleStringProperty typeProperty() { return type; }
    public SimpleObjectProperty<Object> valueProperty() { return value; }
    public SimpleIntegerProperty scopeProperty() { return scope; }

    public void setValue(Object value) { this.value.set(value); }

    @Override
    public String toString() {
        return "Symbol{" + "name=" + name.get() + ", type=" + type.get() +
                ", value=" + value.get() + ", scope=" + scope.get() + '}';
    }
}