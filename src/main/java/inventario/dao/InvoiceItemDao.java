package inventario.dao;

import inventario.model.InvoiceItem;

import java.sql.SQLException;
import java.util.List;

// InvoiceItemDAO.java
public interface InvoiceItemDao {
    void insertarItem(int facturaId, InvoiceItem item) throws SQLException;
    List<InvoiceItem> listarPorFactura(int facturaId) throws SQLException;
}
c