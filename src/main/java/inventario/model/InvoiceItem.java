package inventario.model;

public class InvoiceItem {
    int id;
    Producto producto;
    int cantidad;
    double precioUnitario;

    public InvoiceItem() {
    }

    public InvoiceItem(Producto p, int qty, double costoCompra) {
    }

    double subTotal() { return cantidad * precioUnitario; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
