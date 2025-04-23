package inventario.dao;

import inventario.db.GestorBaseDeDatos;
import inventario.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {
    private final Connection conn;

    public ClienteDaoImpl() throws SQLException {
        this.conn = GestorBaseDeDatos.getInstancia().getConnection();
    }

    @Override
    public void insertar(Cliente c) throws SQLException {
        String sql = "INSERT INTO cliente(nombre, contacto) VALUES (?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getContacto());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void actualizar(Cliente c) throws SQLException {
        String sql = "UPDATE cliente SET nombre=?, contacto=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getContacto());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int clienteId) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM cliente";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            List<Cliente> lista = new ArrayList<>();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setContacto(rs.getString("contacto"));
                lista.add(c);
            }
            return lista;
        }
    }
}