package inventario.service;

import inventario.service.*;
import inventario.dao.*;

public class MainController {
    public final InventarioService inventarioService;
    public final CompraService compraService;
    public final VentaService ventaService;
    public final ReporteService reportService;

    public MainController() throws Exception {
        ProductoDao prodDao = new ProductoDaoImpl();
        ClienteDao cliDao = new ClienteDaoImpl();
        FacturaDao facDao = new FacturaDaoImpl();
        InvoiceItemDao itemDao = new InvoiceItemDaoImpl();

        this.inventarioService = new InventarioService(prodDao);
        this.compraService = new CompraService(facDao, itemDao, inventarioService);
        this.ventaService = new VentaService(facDao, itemDao, inventarioService);
        this.reportService = new ReporteService(facDao);
    }

    // métodos auxiliares para la UI...
}

