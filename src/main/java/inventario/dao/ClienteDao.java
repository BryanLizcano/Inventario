package inventario.dao;

import inventario.model.Cliente;

import java.sql.SQLException;
import java.util.List;

// ClienteDAO.java
public interface ClienteDao {
    void insertar(Cliente c) throws SQLException;
    void actualizar(Cliente c) throws SQLException;
    void eliminar(int clienteId) throws SQLException;
    List<Cliente> listarTodos() throws SQLException;
}
