package com.inventario.service;

import com.inventario.models.Producto;

import java.sql.SQLException;

public class InventarioService {
    private GestorBaseDeDatos db;
    public InventarioService(GestorBaseDeDatos db) { this.db = db; }

    public void agregarProducto(Producto p) throws SQLException {
        String sql = "INSERT INTO producto(nombre,categoria,costoCompra,precioVenta,stock) VALUES(?,?,?,?,?)";
        try (var ps = db.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria().name());
            ps.setDouble(3, p.getPrecioCosto());
            ps.setDouble(4, p.getPrecioVenta());
            ps.setInt(5, p.getStock());
            ps.executeUpdate();
        }
    }
    // editar, eliminar, listar, buscar por categoría…
}