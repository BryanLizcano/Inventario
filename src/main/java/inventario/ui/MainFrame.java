package inventario.ui;

import inventario.controller.MainController;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    private final MainController controller;

    public MainFrame(MainController controller) {
        super("Inventario App");
        this.controller = controller;
        initUI();
        configureModernStyle();
        customizeComponents();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 600));

        // Configurar layout principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Crear pestañas con estilo moderno
        JTabbedPane tabs = new ModernTabbedPane();
        tabs.addTab("Inventario", new InventoryPanel(controller));
        tabs.addTab("Compras", new PurchasePanel(controller));
        tabs.addTab("Ventas", new SalesPanel(controller));
        tabs.addTab("Reportes", new ReportsPanel(controller));

        mainPanel.add(tabs, BorderLayout.CENTER);
        add(mainPanel);

        setLocationRelativeTo(null);
        setIconImage(getAppIcon());
    }

    private void configureModernStyle() {
        try {
            // Usar el Look and Feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Configuraciones personalizadas
            UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 14));
            UIManager.put("Table.selectionBackground", new Color(70, 130, 180));
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("TabbedPane.selectedBackground", new Color(70, 130, 180));

            // Aplicar estilo a componentes
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image getAppIcon() {
        // Ruta absoluta dentro del JAR: "/icons/app-icon.png"
        URL location = getClass().getResource("/icons/app-icon.png");
        if (location == null) {
            System.err.println("¡Icono no encontrado! Ruta inválida.");
            return null;
        }
        return new ImageIcon(location).getImage();
    }

    // Clase interna para pestañas modernas
    private static class ModernTabbedPane extends JTabbedPane {
        public ModernTabbedPane() {
            configureStyle();
        }

        private void configureStyle() {
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
            setBackground(new Color(45, 45, 45));
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        @Override
        public void addTab(String title, Component component) {
            super.addTab(title, component);
            int index = indexOfComponent(component);
            setTabComponentAt(index, new ModernTabTitle(title));
        }
    }

    // Componente personalizado para títulos de pestañas
    private static class ModernTabTitle extends JPanel {
        public ModernTabTitle(String title) {
            setLayout(new BorderLayout());
            setOpaque(false);

            JLabel label = new JLabel(title);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(Color.BLACK);
            label.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

            add(label, BorderLayout.CENTER);
        }
    }

    private void customizeComponents() {
        // Botones
        UIManager.put("Button.background", new Color(70, 130, 180));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));

        // Campos de texto
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));

        // Pestañas
        UIManager.put("TabbedPane.background", new Color(240, 240, 240));
        UIManager.put("TabbedPane.selected", new Color(70, 130, 180));
    }

    public static void main(String[] args) throws Exception {
        MainController ctrl = new MainController();
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(ctrl);
            frame.setVisible(true);
        });
    }
}