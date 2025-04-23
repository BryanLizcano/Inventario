package inventario.service;

import inventario.dao.FacturaDao;
import inventario.dao.InvoiceItemDao;
import inventario.db.GestorBaseDeDatos;
import inventario.model.InvoiceItem;
import inventario.model.PurchaseInvoice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompraService {
    private final FacturaDao facturaDAO;
    private final InvoiceItemDao itemDAO;
    private final InventarioService invService;

    public CompraService(FacturaDao facturaDAO, InvoiceItemDao itemDAO, InventarioService invService) {
        this.facturaDAO = facturaDAO;
        this.itemDAO = itemDAO;
        this.invService = invService;
    }

    public void registrarCompra(PurchaseInvoice inv) throws SQLException {
        Connection conn = GestorBaseDeDatos.getInstancia().getConnection();
        try {
            conn.setAutoCommit(false);
            int idFactura = facturaDAO.crearFactura(inv);
            for (InvoiceItem item : inv.getItems()) {
                itemDAO.insertarItem(idFactura, item);
                invService.actualizarProducto(item.getProducto()); // ajustar stock, implementar en invService
            }
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void registrarCompra(List<InvoiceItem> items, String proveedor, String numeroFactura) {
    }
}
