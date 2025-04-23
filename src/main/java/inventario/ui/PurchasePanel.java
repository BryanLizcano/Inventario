package inventario.ui;

import inventario.controller.MainController;
import inventario.controller.PurchaseController;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchasePanel extends JPanel {
    private final MainController controller;
    private final ProductoTableModel tableModel;

    private JTable table;
    private JTextField txtProveedor;
    private JTextField txtNumeroFactura;
    private JButton btnLoadProducts;
    private JButton btnRegister;

    public PurchasePanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Top form
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtProveedor = new JTextField(15);
        txtNumeroFactura = new JTextField(10);
        form.add(new JLabel("Proveedor:")); form.add(txtProveedor);
        form.add(new JLabel("# Factura:")); form.add(txtNumeroFactura);
        add(form, BorderLayout.NORTH);

        // Table of products to select
        tableModel = new ProductoTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttons = new JPanel();
        btnLoadProducts = new JButton("Cargar Productos");
        btnRegister = new JButton("Registrar Compra");
        buttons.add(btnLoadProducts);
        buttons.add(btnRegister);
        add(buttons, BorderLayout.SOUTH);

        // Actions
        btnLoadProducts.addActionListener(e -> loadProducts());
        btnRegister.addActionListener(e -> registerPurchase());

        loadProducts();
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
            int qty = Integer.parseInt(qtyStr);
            items.add(new InvoiceItem(p, qty, p.getCostoCompra()));
        }
        controller.createPurchase(items, txtProveedor.getText(), txtNumeroFactura.getText());
        JOptionPane.showMessageDialog(this, "Compra registrada.");
        loadProducts();
    }
}
