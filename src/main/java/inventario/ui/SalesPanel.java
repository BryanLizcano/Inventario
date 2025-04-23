package inventario.ui;

import inventario.controller.MainController;
import inventario.controller.SalesController;
import inventario.model.Cliente;
import inventario.model.InvoiceItem;
import inventario.model.Producto;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesPanel extends JPanel {
    private final MainController controller;
    private final ProductoTableModel tableModel;

    private JTable table;
    private JTextField txtCliente;
    private JButton btnLoad;
    private JButton btnRegister;

    public SalesPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCliente = new JTextField(15);
        form.add(new JLabel("Cliente (opcional):")); form.add(txtCliente);
        add(form, BorderLayout.NORTH);

        tableModel = new ProductoTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        btnLoad = new JButton("Cargar Productos");
        btnRegister = new JButton("Registrar Venta");
        buttons.add(btnLoad);
        buttons.add(btnRegister);
        add(buttons, BorderLayout.SOUTH);

        btnLoad.addActionListener(e -> loadProducts());
        btnRegister.addActionListener(e -> registerSale());

        loadProducts();
    }

    private void loadProducts() {
        List<Producto> list = controller.getAllProducts();
        tableModel.setProductos(list);
    }

    private void registerSale() {
        int[] rows = table.getSelectedRows();
        if (rows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona productos.");
            return;
        }
        List<InvoiceItem> items = new ArrayList<>();
        for (int r : rows) {
            Producto p = controller.getAllProducts().get(r);
            int qty = Integer.parseInt(JOptionPane.showInputDialog(this, "Cantidad para " + p.getNombre()));
            items.add(new InvoiceItem(p, qty, p.getPrecioVenta()));
        }
        Cliente c = txtCliente.getText().isBlank() ? null : new Cliente(0, txtCliente.getText(), "");
        controller.createSale(items, c);
        JOptionPane.showMessageDialog(this, "Venta registrada.");
        loadProducts();
    }
}
