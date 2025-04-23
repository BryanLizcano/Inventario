package inventario.db;

import java.sql.*;

public class GestorBaseDeDatos {
    private static final String URL = "jdbc:sqlite:inventario.db";
    private static GestorBaseDeDatos instancia;
    private Connection conn;

    private GestorBaseDeDatos() throws SQLException {
        conn = DriverManager.getConnection(URL);
        inicializarEsquema();
    }

    public static synchronized GestorBaseDeDatos getInstancia()
            throws SQLException {
        if (instancia == null) instancia = new GestorBaseDeDatos();
        return instancia;
    }

    public Connection getConnection() {
        return conn;
    }

    private void inicializarEsquema() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("PRAGMA foreign_keys = ON;");
            // Tabla de productos
            st.executeUpdate("CREATE TABLE IF NOT EXISTS producto ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL,"
                    + "categoria TEXT NOT NULL,"
                    + "costoCompra REAL NOT NULL CHECK(costoCompra >= 0),"
                    + "precioVenta REAL NOT NULL CHECK(precioVenta >= 0),"
                    + "stock INTEGER NOT NULL DEFAULT 0 CHECK(stock >= 0)"
                    + ");");
            // Tabla de clientes
            st.executeUpdate("CREATE TABLE IF NOT EXISTS cliente ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL,"
                    + "contacto TEXT"
                    + ");");
            // Tabla de facturas
            st.executeUpdate("CREATE TABLE IF NOT EXISTS factura ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "tipo TEXT NOT NULL CHECK(tipo IN ('COMPRA','VENTA')),"
                    + "fecha TEXT NOT NULL,"
                    + "proveedor TEXT,"
                    + "numero_factura TEXT,"
                    + "cliente_id INTEGER,"
                    + "estado TEXT NOT NULL DEFAULT 'CERRADA' CHECK(estado IN ('ABIERTA','CERRADA')),"
                    + "FOREIGN KEY(cliente_id) REFERENCES cliente(id)"
                    + ");");
            // Tabla de items
            st.executeUpdate("CREATE TABLE IF NOT EXISTS invoice_item ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "factura_id INTEGER NOT NULL,"
                    + "producto_id INTEGER NOT NULL,"
                    + "cantidad INTEGER NOT NULL CHECK(cantidad > 0),"
                    + "precio_unitario REAL NOT NULL CHECK(precio_unitario >= 0),"
                    + "FOREIGN KEY(factura_id) REFERENCES factura(id) ON DELETE CASCADE,"
                    + "FOREIGN KEY(producto_id) REFERENCES producto(id)"
                    + ");");
            // Opcional: historial de costos
            st.executeUpdate("CREATE TABLE IF NOT EXISTS costo_historico ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "producto_id INTEGER NOT NULL,"
                    + "fecha_cambio TEXT NOT NULL,"
                    + "costo_anterior REAL NOT NULL,"
                    + "costo_nuevo REAL NOT NULL,"
                    + "FOREIGN KEY(producto_id) REFERENCES producto(id)"
                    + ");");
            // Índices
            st.executeUpdate("CREATE INDEX IF NOT EXISTS idx_producto_categoria ON producto(categoria);");
            st.executeUpdate("CREATE INDEX IF NOT EXISTS idx_factura_fecha ON factura(fecha);");
            st.executeUpdate("CREATE INDEX IF NOT EXISTS idx_item_factura ON invoice_item(factura_id);");
        }
    }
}
