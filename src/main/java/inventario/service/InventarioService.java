package inventario.service;

import inventario.dao.ProductoDao;
import inventario.model.Producto;
import inventario.model.Categoria;
import java.sql.SQLException;
import java.util.List;

public class InventarioService {
    private final ProductoDao productoDAO;

    public InventarioService(ProductoDao productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void agregarProducto(Producto p) throws SQLException {
        productoDAO.insertar(p);
    }

    public void actualizarProducto(Producto p) throws SQLException {
        productoDAO.actualizar(p);
    }

    public void eliminarProducto(int productoId) throws SQLException {
        productoDAO.eliminar(productoId);
    }

    public List<Producto> listarTodos() throws SQLException {
        return productoDAO.listarTodos();
    }

    public List<Producto> listarPorCategoria(Categoria c) throws SQLException {
        return productoDAO.buscarPorCategoria(c);
    }

    public void ajustarStock(int productoId, int delta) throws SQLException {
        // 1. Cargar el producto desde la BD
        Producto p = productoDAO.obtenerPorId(productoId);
        if (p == null) {
            throw new SQLException("Producto no encontrado con id=" + productoId);
        }
        // 2. Ajustar stock
        p.setStock(p.getStock() + delta);
        if (p.getStock() < 0) p.setStock(0); // opcional: evitar negativos
        // 3. Persistir el nuevo stock
        productoDAO.actualizar(p);
    }
}

