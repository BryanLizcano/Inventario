package inventario.controller;

import inventario.model.*;
import inventario.service.CompraService;
import inventario.service.InventarioService;
import inventario.service.ReporteService;
import inventario.service.VentaService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MainController {
    private final InventarioService inventarioService;
    private final CompraService compraService;
    private final VentaService ventaService;
    private final ReporteService reportService;

    public MainController(InventarioService invSvc,
                          CompraService compSvc,
                          VentaService vendSvc,
                          ReporteService repSvc) {
        this.inventarioService = invSvc;
        this.compraService = compSvc;
        this.ventaService = vendSvc;
        this.reportService = repSvc;
    }

    // --- Inventario ---
    public void agregarProducto(String nombre, Categoria categoria, double costoCompra,
                                double precioVenta, int stock) throws SQLException {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setCategoria(categoria);
        p.setCostoCompra(costoCompra);
        p.setPrecioVenta(precioVenta);
        p.setStock(stock);
        inventarioService.agregarProducto(p);
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        inventarioService.actualizarProducto(producto);
    }

    public void eliminarProducto(int productoId) throws SQLException {
        inventarioService.eliminarProducto(productoId);
    }

    public List<Producto> listarTodosProductos() throws SQLException {
        return inventarioService.listarTodos();
    }

    public List<Producto> listarProductosPorCategoria(Categoria categoria) throws SQLException {
        return inventarioService.listarPorCategoria(categoria);
    }

    // --- Compras ---
    public void registrarCompra(List<InvoiceItem> items,
                                String proveedor,
                                String numeroFactura) throws SQLException {
        PurchaseInvoice inv = new PurchaseInvoice();
        inv.setProveedor(proveedor);
        inv.setNumeroFactura(numeroFactura);
        inv.getItems().addAll(items);
        compraService.registrarCompra(inv);
    }

    // --- Ventas ---
    public void registrarVenta(List<InvoiceItem> items, Cliente cliente) throws SQLException {
        SaleInvoice inv = cliente != null
                ? new SaleInvoice(cliente)
                : new SaleInvoice();
        inv.getItems().addAll(items);
        ventaService.registrarVenta(inv);
    }

    // --- Reportes ---
    public Map<LocalDate, Double> obtenerGananciasRango(LocalDate desde, LocalDate hasta)
            throws SQLException {
        return reportService.gananciasPorRango(desde, hasta);
    }
}
