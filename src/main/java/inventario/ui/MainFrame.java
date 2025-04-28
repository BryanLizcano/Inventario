package inventario.ui;

import inventario.ui.componets.PurchasePanel;
import inventario.ui.componets.ReportPanel;
import inventario.ui.componets.SalesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPane;

    public MainFrame() {
        setTitle("Sistema de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        // Instantiate panels
        PurchasePanel purchasePanel = new PurchasePanel();
        SalesPanel salesPanel = new SalesPanel();
        ReportPanel reportPanel = new ReportPanel();
        InventoryPanel inventoryPanel = new InventoryPanel();

        // Add them to contentPane
        contentPane.add(purchasePanel, "purchase");
        contentPane.add(salesPanel, "sales");
        contentPane.add(reportPanel, "report");
        contentPane.add(inventoryPanel, "inventory");

        setJMenuBar(createMenuBar());
        add(contentPane);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Paneles");
        JMenuItem miPurchase = new JMenuItem("Compras");
        JMenuItem miSales = new JMenuItem("Ventas");
        JMenuItem miReports = new JMenuItem("Reportes");
        JMenuItem miInventory = new JMenuItem("Inventario");

        miPurchase.addActionListener(e -> cardLayout.show(contentPane, "purchase"));
        miSales.addActionListener(e -> cardLayout.show(contentPane, "sales"));
        miReports.addActionListener(e -> cardLayout.show(contentPane, "report"));
        miInventory.addActionListener(e -> cardLayout.show(contentPane, "inventory"));

        menu.add(miPurchase);
        menu.add(miSales);
        menu.add(miReports);
        menu.add(miInventory);
        menuBar.add(menu);
        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

