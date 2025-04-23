package com.inventario.controllers;

import com.inventario.models.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class InventarioController {
    @FXML
    private TableView<Producto> tablaProductos;

    private InventarioService inventarioService = new InventarioService();

    @FXML
    public void initialize() {
        cargarProductos();
    }

    private void cargarProductos() {
        try {
            tablaProductos.getItems().setAll(inventarioService.obtenerTodosProductos());
        } catch (InventarioException e) {
            mostrarError("Error al cargar productos: " + e.getMessage());
        }
    }

    @FXML
    private void handleAgregarProducto() {
        // Lógica para abrir diálogo de nuevo producto
    }
}
