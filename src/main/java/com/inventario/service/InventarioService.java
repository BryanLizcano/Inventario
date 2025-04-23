package com.inventario.service;

public class InventarioService {
    private ProductoDAO productoDao = new ProductoDAOImpl();
    private VentaDAO ventaDao = new VentaDAOImpl();

    public void realizarVenta(Venta venta) throws InventarioException {
        try {
            // Validar stock
            for (DetalleVenta detalle : venta.getDetalles()) {
                Producto p = productoDao.buscarPorId(detalle.getProducto().getId());
                if (p.getStock() < detalle.getCantidad()) {
                    throw new InventarioException("Stock insuficiente para: " + p.getNombre());
                }
            }

            // Actualizar base de datos
            ventaDao.guardarVenta(venta);
            actualizarStocks(venta);

        } catch (SQLException e) {
            throw new InventarioException("Error en base de datos: " + e.getMessage());
        }
    }

    private void actualizarStocks(Venta venta) throws SQLException {
        for (DetalleVenta detalle : venta.getDetalles()) {
            productoDao.actualizarStock(
                    detalle.getProducto().getId(),
                    -detalle.getCantidad()
            );
        }
    }
}
