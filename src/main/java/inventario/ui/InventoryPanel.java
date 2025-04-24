package inventario.ui;

import inventario.controller.MainController;
import inventario.model.Categoria;
import inventario.model.Producto;
import inventario.ui.swing.ModernTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class InventoryPanel extends JPanel {
    private final MainController controller;
    private final ModernTable table;
    private final ProductoTableModel model;

    public InventoryPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        model = new ProductoTableModel();
        table = new ModernTable();
        table.setModel(model); // Establecer el modelo después de la inicialización
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton btnRefresh = createStyledButton("Refrescar", new Color(70, 130, 180));
        btnRefresh.addActionListener(e -> loadData());

        JButton btnAgregar = createStyledButton("Agregar Producto", new Color(56, 142, 60));
        btnAgregar.addActionListener(e -> mostrarFormularioAgregarProducto());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.add(btnRefresh);
        panelBotones.add(btnAgregar);
        panelBotones.setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        loadData();
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker()),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        return button;
    }

    private void loadData() {
        try {
            List<Producto> lista = controller.inventarioService.listarTodos();
            model.setProductos(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void mostrarFormularioAgregarProducto() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField nombreField = createStyledTextField();
        JComboBox<Categoria> categoriaComboBox = createStyledComboBox();
        JTextField costoCompraField = createStyledTextField();
        JTextField precioVentaField = createStyledTextField();
        JTextField stockField = createStyledTextField();

        panel.add(createFormLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(createFormLabel("Categoría:"));
        panel.add(categoriaComboBox);
        panel.add(createFormLabel("Costo Compra:"));
        panel.add(costoCompraField);
        panel.add(createFormLabel("Precio Venta:"));
        panel.add(precioVentaField);
        panel.add(createFormLabel("Stock:"));
        panel.add(stockField);

        // Cambiar color del texto de botones y mensaje a negro
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Producto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                double costoCompra = Double.parseDouble(costoCompraField.getText());
                double precioVenta = Double.parseDouble(precioVentaField.getText());
                int stock = Integer.parseInt(stockField.getText());

                controller.agregarProducto(nombre, categoria, costoCompra, precioVenta, stock);
                loadData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar producto: " + ex.getMessage());
            }
        }
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JComboBox<Categoria> createStyledComboBox() {
        JComboBox<Categoria> combo = new JComboBox<>(Categoria.values());
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return this;
            }
        });
        return combo;
    }
}
