package inventario.controller;

import inventario.dao.ProductoDaoImpl;
import inventario.model.Cliente;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
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

    /** Obtiene todos los productos para poblar la tabla en la UI */
    public List<Producto> getAllProducts() throws SQLException {
        return new ProductoDaoImpl().listarTodos();
    }}
