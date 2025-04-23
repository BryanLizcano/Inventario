package com.inventario.service;

import com.inventario.model.PurchaseInvoice;

public class CompraService {
    private GestorBaseDeDatos db;
    private InventarioService invService;
    public void registrarCompra(PurchaseInvoice pi) {
        // guardar pi + items + actualizar stock (+) y costoCompra
    }
}
