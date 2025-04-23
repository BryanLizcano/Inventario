package inventario.ui;

import inventario.controller.MainController;
import inventario.model.Cliente;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import inventario.ui.swing.ModernTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesPanel extends JPanel {
    private final MainController controller;
    private final ProductoTableModel tableModel;
    private JTable table;
    private JTextField txtCliente = createStyledTextField(15);
    private JButton btnLoad;
    private JButton btnRegister;

    public SalesPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = createFormPanel();
        add(form, BorderLayout.NORTH);

        tableModel = new ProductoTableModel();
        table = new ModernTable();
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttons = createButtonsPanel();
        add(buttons, BorderLayout.SOUTH);

        loadProducts();
    }

    private void loadProducts() {
        List<Producto> list = controller.getAllProducts();
        tableModel.setProductos(list);
    }

    private JPanel createFormPanel() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        form.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        form.add(createFormLabel("Cliente (opcional):"));
        form.add(txtCliente);
        return form;
    }

    private JPanel createButtonsPanel() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);

        btnLoad = createStyledButton("Cargar Productos", new Color(70, 130, 180));
        btnRegister = createStyledButton("Registrar Venta", new Color(56, 142, 60));

        // Listeners...

        buttons.add(btnLoad);
        buttons.add(btnRegister);
        return buttons;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker()),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        return button;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private JTextField createStyledTextField(int i) {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private void registerSale() {
        int[] rows = table.getSelectedRows();
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un producto.");
            return;
        }
        try {
            List<InvoiceItem> items = new ArrayList<>();
            for (int r : rows) {
                Producto p = controller.getAllProducts().get(r);
                String qtyStr = JOptionPane.showInputDialog(
                        this, "Cantidad para " + p.getNombre() + ":");
                int qty = Integer.parseInt(qtyStr);
                items.add(new InvoiceItem(p, qty, p.getPrecioVenta()));
            }
            Cliente cliente = txtCliente.getText().isBlank()
                    ? null
                    : new Cliente(0, txtCliente.getText(), "");
            controller.createSale(items, cliente);

            JOptionPane.showMessageDialog(this,
                    "Venta registrada correctamente.");
            // Refrescar stock y tabla
            btnLoad.doClick();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ingresa un número válido para la cantidad.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
