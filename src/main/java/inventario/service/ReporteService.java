package inventario.service;

import inventario.dao.FacturaDao;
import inventario.model.Invoice;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReporteService {
    private final FacturaDao facturaDAO;

    public ReporteService(FacturaDao facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    public Map<LocalDate, Double> gananciasPorRango(LocalDate desde, LocalDate hasta) throws SQLException {
        List<Invoice> facturas = facturaDAO.listarPorRango(desde, hasta);
        return facturas.stream()
                .collect(Collectors.groupingBy(
                        Invoice::getFecha,
                        Collectors.summingDouble(Invoice::calcularTotal)
                ));
    }
}

