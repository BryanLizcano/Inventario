package inventario.model;

public enum Categoria {
    ELECTRONICA("Electrónica"),
    ROPA("Ropa"),
    ALIMENTOS("Alimentos"),
    DEFAULT("default");

    private final String nombre;

    Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
