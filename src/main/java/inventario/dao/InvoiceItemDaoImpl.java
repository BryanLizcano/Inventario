package inventario.dao;

import inventario.db.GestorBaseDeDatos;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceItemDaoImpl implements InvoiceItemDao {
    private final Connection conn;

    public InvoiceItemDaoImpl() throws SQLException {
        this.conn = GestorBaseDeDatos.getInstancia().getConnection();
    }

    @Override
    public void insertarItem(int facturaId, InvoiceItem item) throws SQLException {
        String sql = "INSERT INTO invoice_item(factura_id, producto_id, cantidad, precio_unitario) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, facturaId);
            ps.setInt(2, item.getProducto().getId());
            ps.setInt(3, item.getCantidad());
            ps.setDouble(4, item.getPrecioUnitario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) item.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public List<InvoiceItem> listarPorFactura(int facturaId) throws SQLException {
        String sql = "SELECT * FROM invoice_item WHERE factura_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, facturaId);
            try (ResultSet rs = ps.executeQuery()) {
                List<InvoiceItem> items = new ArrayList<>();
                while (rs.next()) {
                    InvoiceItem item = new InvoiceItem();
                    item.setId(rs.getInt("id"));
                    Producto p = new Producto();
                    p.setId(rs.getInt("producto_id"));
                    item.setProducto(p);
                    item.setCantidad(rs.getInt("cantidad"));
                    item.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    items.add(item);
                }
                return items;
            }
        }
    }
}
