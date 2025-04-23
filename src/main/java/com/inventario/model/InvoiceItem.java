package com.inventario.model;

import com.inventario.models.Producto;

public class InvoiceItem {
    private Producto producto;
    private int cantidad;
    private double precioUnitario; // puede ser costo o venta
    public double subTotal() {
        return cantidad * precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
