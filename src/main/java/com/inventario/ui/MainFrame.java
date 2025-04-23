package com.inventario.ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Inventario y Ventas");
        configureFrame();
        initComponents();
    }

    private void configureFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
    }

    private void initComponents() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Inventario", new InventoryPanel());
        tabs.add("Ventas", new SalesPanel());
        tabs.add("Compras", new PurchasePanel());
        tabs.add("Cuentas Cliente", new ClientAccountPanel());
        tabs.add("Reportes", new ReportsPanel());

        add(tabs);
    }
}