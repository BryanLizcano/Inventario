package com.inventario.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDeDatos {
    private static final String URL = "jdbc:sqlite:inventario.db";
    private Connection conn;

    public GestorBaseDeDatos() throws SQLException {
        conn = DriverManager.getConnection(URL);
        inicializarTablas();
    }
    private void inicializarTablas() throws SQLException {
        String ddlProducto = """    
            CREATE TABLE IF NOT EXISTS producto (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              nombre TEXT, categoria TEXT,
              costoCompra REAL, precioVenta REAL, stock INTEGER
            );
        """;
        // similar para factura_compra, factura_venta, cliente, invoice_item…
        try (Statement st = conn.createStatement()) {
            st.execute(ddlProducto);
            // …
        }
    }
    public Connection getConnection() { return conn; }
}
