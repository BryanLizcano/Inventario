package inventario.ui;

import inventario.controller.MainController;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import inventario.ui.swing.ModernTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PurchasePanel extends JPanel {
    private final MainController controller;
    private final ModernTable table;
    private final ProductoTableModel tableModel;
    private JTextField txtProveedor;
    private JTextField txtNumeroFactura;
    private JButton btnLoadProducts;
    private JButton btnRegister;

    public PurchasePanel(MainController controller) {
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

    private JPanel createFormPanel() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        form.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        txtProveedor = createStyledTextField(15);
        txtNumeroFactura = createStyledTextField(10);

        form.add(createFormLabel("Proveedor:"));
        form.add(txtProveedor);
        form.add(createFormLabel("# Factura:"));
        form.add(txtNumeroFactura);

        return form;
    }

    private JPanel createButtonsPanel() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);

        btnLoadProducts = createStyledButton("Cargar Productos", new Color(70, 130, 180));
        btnRegister = createStyledButton("Registrar Compra", new Color(56, 142, 60));

        btnLoadProducts.addActionListener(e -> loadProducts());
        btnRegister.addActionListener(e -> registerPurchase());

        buttons.add(btnLoadProducts);
        buttons.add(btnRegister);
        return buttons;
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

    private void loadProducts() {
        List<Producto> list = controller.getAllProducts();
        tableModel.setProductos(list);
    }

    private void registerPurchase() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un producto.");
            return;
        }
        List<InvoiceItem> items = new ArrayList<>();
        for (int row : selectedRows) {
            Producto p = controller.getAllProducts().get(row);
            String qtyStr = JOptionPane.showInputDialog(this, "Cantidad para " + p.getNombre() + ":");
            if (qtyStr == null || qtyStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede estar vacía.");
                return;
            }
            int qty = Integer.parseInt(qtyStr);
            items.add(new InvoiceItem(p, qty, p.getCostoCompra()));
        }
        controller.createPurchase(items, txtProveedor.getText(), txtNumeroFactura.getText());
        JOptionPane.showMessageDialog(this, "Compra registrada.");
        loadProducts();
    }
}
