package com.inventario.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta {
    private int id;
    private LocalDateTime fecha;
    private Cliente cliente;
    private double total;
    private List<DetalleVenta> detalles = new ArrayList<>();

    // Constructor
    public Venta() {}

    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
    }

    public boolean esVentaAnonima() {
        return cliente == null;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return id == venta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}