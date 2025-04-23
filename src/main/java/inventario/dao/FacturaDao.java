package inventario.dao;

import inventario.model.Invoice;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

// FacturaDAO.java
public interface FacturaDao {
    int crearFactura(Invoice inv) throws SQLException;
    Invoice obtenerPorId(int facturaId) throws SQLException;
    List<Invoice> listarPorRango(LocalDate desde, LocalDate hasta) throws SQLException;
}
