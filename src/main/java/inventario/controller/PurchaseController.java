package inventario.controller;

import inventario.model.InvoiceItem;
import inventario.service.CompraService;

import java.sql.SQLException;
import java.util.List;

public class PurchaseController {
    private final CompraService service;

    public PurchaseController(CompraService service) {
        this.service = service;
    }

    public void createPurchase(List<InvoiceItem> items, String proveedor, String numeroFactura)
            throws SQLException {
        service.registrarCompra(items, proveedor, numeroFactura);
    }
}
