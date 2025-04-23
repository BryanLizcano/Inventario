package inventario.dao;

import inventario.db.GestorBaseDeDatos;
import inventario.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaDaoImpl implements FacturaDao {
    private final Connection conn;
    private final InvoiceItemDao itemDAO;

    public FacturaDaoImpl() throws SQLException {
        this.conn = GestorBaseDeDatos.getInstancia().getConnection();
        this.itemDAO = new InvoiceItemDaoImpl();
    }

    @Override
    public int crearFactura(Invoice inv) throws SQLException {
        String sql = "INSERT INTO factura(tipo, fecha, proveedor, numero_factura, cliente_id, estado) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, inv instanceof PurchaseInvoice ? "COMPRA" : "VENTA");
            ps.setString(2, inv.getFecha().toString());
            if (inv instanceof PurchaseInvoice) {
                PurchaseInvoice pi = (PurchaseInvoice) inv;
                ps.setString(3, pi.getProveedor());
                ps.setString(4, pi.getNumeroFactura());
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setNull(3, Types.VARCHAR);
                ps.setNull(4, Types.VARCHAR);
                ps.setInt(5, ((SaleInvoice) inv).getCliente().getId());
            }
            ps.setString(6, "CERRADA");
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    inv.setId(id);
                    return id;
                }
            }
        }
        throw new SQLException("No se pudo obtener el ID de la factura.");
    }

    @Override
    public Invoice obtenerPorId(int facturaId) throws SQLException {
        String sql = "SELECT * FROM factura WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, facturaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                    Invoice inv = "COMPRA".equals(tipo)
                            ? new PurchaseInvoice()
                            : new SaleInvoice();
                    inv.setId(facturaId);
                    inv.setFecha(fecha);
                    if (inv instanceof PurchaseInvoice) {
                        ((PurchaseInvoice) inv).setProveedor(rs.getString("proveedor"));
                        ((PurchaseInvoice) inv).setNumeroFactura(rs.getString("numero_factura"));
                    } else {
                        Cliente c = new Cliente();
                        c.setId(rs.getInt("cliente_id"));
                        ((SaleInvoice) inv).setCliente(c);
                    }
                    inv.setItems(itemDAO.listarPorFactura(facturaId));
                    return inv;
                }
            }
        }
        return null;
    }

    @Override
    public List<Invoice> listarPorRango(LocalDate desde, LocalDate hasta) throws SQLException {
        String sql = "SELECT id FROM factura WHERE fecha BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, desde.toString());
            ps.setString(2, hasta.toString());
            List<Invoice> list = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice inv = obtenerPorId(rs.getInt("id"));
                    if (inv != null) list.add(inv);
                }
            }
            return list;
        }
    }
}

