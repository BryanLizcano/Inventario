package inventario.controller;

import inventario.service.ReporteService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class ReportController {
    private final ReporteService service;

    public ReportController(ReporteService service) {
        this.service = service;
    }

    public Map<LocalDate, Double> getProfitReport(LocalDate from, LocalDate to) throws SQLException {
        return service.gananciasPorRango(from, to);
    }
}

