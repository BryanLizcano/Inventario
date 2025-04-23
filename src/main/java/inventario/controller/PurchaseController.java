package inventario.controller;

import inventario.dao.ProductoDaoImpl;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
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

    public List<Producto> getAllProducts() throws SQLException {
        // Delegamos directamente al DAO de productos
        return new ProductoDaoImpl().listarTodos();
    }
}
