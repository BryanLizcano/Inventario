package inventario.ui;
import inventario.controller.MainController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final MainController controller;

    public MainFrame(MainController controller) {
        super("Inventario App");
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Inventario", new InventoryPanel(controller));
        tabs.addTab("Compras", new PurchasePanel(controller));
        tabs.addTab("Ventas", new SalesPanel(controller));
        tabs.addTab("Reportes", new ReportsPanel(controller));
        add(tabs, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws Exception {
        MainController ctrl = new MainController();
        SwingUtilities.invokeLater(() -> new MainFrame(ctrl).setVisible(true));
    }
}
