package inventario.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SaleInvoice extends Invoice{
    private Cliente cliente;   // null = venta al contado

    public SaleInvoice() {
        this.fecha = LocalDate.now();
        this.items = new ArrayList<>();
    }

    public SaleInvoice(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /** Añade un item de venta a la factura */
    public void addItem(InvoiceItem item) {
        this.items.add(item);
    }

    /** Calcula el total (heredado de Invoice) */
    @Override
    public double calcularTotal() {
        return super.calcularTotal();
    }

    @Override
    public String toString() {
        return "SaleInvoice{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", cliente=" + (cliente != null ? cliente.getNombre() : "CONTADO") +
                ", total=" + calcularTotal() +
                '}';
    }
}
