package inventario.controller;

import inventario.model.Categoria;
import inventario.model.Producto;
import inventario.service.InventarioService;

import java.sql.SQLException;
import java.util.List;

public class InventoryController {
    private final InventarioService service;

    public InventoryController(InventarioService service) {
        this.service = service;
    }

    public void addProduct(Producto p) throws SQLException {
        service.agregarProducto(p);
    }

    public void updateProduct(Producto p) throws SQLException {
        service.actualizarProducto(p);
    }

    public void deleteProduct(int id) throws SQLException {
        service.eliminarProducto(id);
    }

    public List<Producto> getAllProducts() throws SQLException {
        return service.listarTodos();
    }

    public List<Producto> getProductsByCategory(Categoria c) throws SQLException {
        return service.listarPorCategoria(c);
    }
}

