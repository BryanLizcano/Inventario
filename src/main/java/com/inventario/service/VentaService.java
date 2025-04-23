package com.inventario.service;

import com.inventario.model.CuentaCliente;
import com.inventario.model.SaleInvoice;

public class VentaService {
    private GestorBaseDeDatos db;
    private InventarioService invService;
    public VentaService(GestorBaseDeDatos db, InventarioService inv) {
        this.db = db; this.invService = inv;
    }
    public void registrarVentaSuelta(SaleInvoice sale) {
        // guardar sale + items + actualizar stock (-)
    }
    public void registrarVentaCuenta(CuentaCliente account) {
        // al cerrar cuenta: guardar todas las ventas
    }
}
