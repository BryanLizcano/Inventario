package com.inventario.service;

import com.inventario.model.Producto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReportService {
    private GestorBaseDeDatos db;
    public Map<LocalDate, Double> gananciasSemana(int semanaAnio) {
        // consulta JDBC: SUM(precioVenta–costoCompra) agrupado por día
        return new HashMap<>();
    }
    public Map<Producto, Double> variacionCostos(LocalDate desde, LocalDate hasta) {
        // consulta historiales de compras
        return new HashMap<>();
    }
}
