package inventario.dao;

import inventario.db.GestorBaseDeDatos;
import inventario.model.Categoria;
import inventario.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ProductoDAOImpl.java
public class ProductoDaoImpl implements ProductoDao {
    private final Connection conn = GestorBaseDeDatos.getInstancia().getConnection();

    public ProductoDaoImpl() throws SQLException {
    }

    @Override
    public void insertar(Producto p) throws SQLException {
        String sql = "INSERT INTO producto(nombre, categoria, costoCompra, precioVenta, stock) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria().name());
            ps.setDouble(3, p.getCostoCompra());
            ps.setDouble(4, p.getPrecioVenta());
            ps.setInt(5, p.getStock());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void actualizar(Producto p) throws SQLException {
        String sql = "UPDATE producto SET nombre=?, categoria=?, costoCompra=?, precioVenta=?, stock=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria().name());
            ps.setDouble(3, p.getCostoCompra());
            ps.setDouble(4, p.getPrecioVenta());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int productoId) throws SQLException {
        String sql = "DELETE FROM producto WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Producto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM producto";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            List<Producto> lista = new ArrayList<>();
            while (rs.next()) {
                Producto p = mapearProducto(rs);
                lista.add(p);
            }
            return lista;
        }
    }

    @Override
    public List<Producto> buscarPorCategoria(Categoria c) throws SQLException {
        String sql = "SELECT * FROM producto WHERE categoria=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.name());
            try (ResultSet rs = ps.executeQuery()) {
                List<Producto> lista = new ArrayList<>();
                while (rs.next()) lista.add(mapearProducto(rs));
                return lista;
            }
        }
    }

    @Override
    public Producto obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                } else {
                    return null;  // O lanzar excepción si prefieres
                }
            }
        }
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCategoria(Categoria.valueOf(rs.getString("categoria")));
        p.setCostoCompra(rs.getDouble("costoCompra"));
        p.setPrecioVenta(rs.getDouble("precioVenta"));
        p.setStock(rs.getInt("stock"));
        return p;
    }
}
