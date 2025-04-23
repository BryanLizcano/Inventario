package inventario.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Invoice {
    int id;
    LocalDate fecha = LocalDate.now();
    List<InvoiceItem> items = new ArrayList<>();

    public Invoice() {

    }

    public double calcularTotal() {
        return items.stream()
                .mapToDouble(InvoiceItem::subTotal)
                .sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }


}
