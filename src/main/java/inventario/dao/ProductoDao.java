package inventario.dao;

import inventario.model.Categoria;
import inventario.model.Cliente;
import inventario.model.Producto;

import java.sql.SQLException;
import java.util.List;

// ProductoDAO.java
public interface ProductoDao {
    void insertar(Producto p) throws SQLException;
    void actualizar(Producto p) throws SQLException;
    void eliminar(int productoId) throws SQLException;
    List<Producto> listarTodos() throws SQLException;
    List<Producto> buscarPorCategoria(Categoria c) throws SQLException;
    Producto obtenerPorId(int id) throws SQLException;
    void desabilitar(int productoId) throws SQLException;
}







