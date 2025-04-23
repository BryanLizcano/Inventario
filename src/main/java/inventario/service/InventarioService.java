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
}

