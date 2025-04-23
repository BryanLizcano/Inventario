package inventario.controller;

import inventario.model.Cliente;
import inventario.model.InvoiceItem;
import inventario.service.VentaService;

import java.sql.SQLException;
import java.util.List;

public class SalesController {
    private final VentaService service;

    public SalesController(VentaService service) {
        this.service = service;
    }

    public void createSale(List<InvoiceItem> items, Cliente cliente) throws SQLException {
        service.registrarVenta(items, cliente);
    }
}
