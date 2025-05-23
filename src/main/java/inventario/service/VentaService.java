package inventario.service;

import inventario.dao.FacturaDao;
import inventario.dao.InvoiceItemDao;
import inventario.model.Cliente;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import inventario.model.SaleInvoice;
import inventario.db.GestorBaseDeDatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VentaService {
    private final FacturaDao facturaDAO;
    private final InvoiceItemDao itemDAO;
    private final InventarioService invService;

    public VentaService(FacturaDao facturaDAO, InvoiceItemDao itemDAO, InventarioService invService) {
        this.facturaDAO = facturaDAO;
        this.itemDAO = itemDAO;
        this.invService = invService;
    }

    public void registrarVenta(SaleInvoice inv) throws SQLException {
        Connection conn = GestorBaseDeDatos.getInstancia().getConnection();
        try {
            conn.setAutoCommit(false);
            int idFactura = facturaDAO.crearFactura(inv);

            for (InvoiceItem item : inv.getItems()) {
                itemDAO.insertarItem(idFactura, item);
                Producto p = item.getProducto();
                // 1) Reducir el stock en memoria
                p.setStock(p.getStock() - item.getCantidad());
                // 2) Persistir el cambio
                invService.actualizarProducto(p);
            }

            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }


}
