package com.inventario.model;

import java.util.ArrayList;
import java.util.List;

public class CuentaCliente {
    private Cliente cliente;
    private List<SaleInvoice> ventasAbiertas = new ArrayList<>();
    public void abrirVenta(SaleInvoice inv) {
        ventasAbiertas.add(inv);
    }
    public SaleInvoice cerrarCuenta() {
        // sumar todas y devolver una factura global, luego limpiar lista
        double total = ventasAbiertas.stream()
                .mapToDouble(SaleInvoice::calcularTotal).sum();
        // construir SaleInvoice resumen…
        ventasAbiertas.clear();
        // return facturaResumen;
        return null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<SaleInvoice> getVentasAbiertas() {
        return ventasAbiertas;
    }

    public void setVentasAbiertas(List<SaleInvoice> ventasAbiertas) {
        this.ventasAbiertas = ventasAbiertas;
    }
}
