package inventario.controller;

import inventario.dao.ProductoDaoImpl;
import inventario.model.Cliente;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import inventario.model.SaleInvoice;
import inventario.service.VentaService;

import java.sql.SQLException;
import java.util.List;

public class SalesController {
    private final VentaService service;

    public SalesController(VentaService service) {
        this.service = service;
    }

    public void createSale(List<InvoiceItem> items, Cliente cliente) throws SQLException {
        // 1) Construir la factura de venta
        SaleInvoice inv = (cliente != null)
                ? new SaleInvoice(cliente)
                : new SaleInvoice();
        inv.getItems().addAll(items);

        // 2) Delegar al servicio
        service.registrarVenta(inv);
    }

    /** Obtiene todos los productos para poblar la tabla en la UI */
    public List<Producto> getAllProducts() throws SQLException {
        return new ProductoDaoImpl().listarTodos();
    }}
