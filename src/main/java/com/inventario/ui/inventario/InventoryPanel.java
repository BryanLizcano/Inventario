package com.inventario.ui.inventario;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private final InventoryController controller;

    public InventoryPanel() {
        controller = new InventoryController();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Tabla de productos
        JTable table = new JTable(controller.getInventoryModel());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Agregar Producto"));
        buttonPanel.add(new JButton("Actualizar Precio"));

        add(buttonPanel, BorderLayout.SOUTH);
    }
}