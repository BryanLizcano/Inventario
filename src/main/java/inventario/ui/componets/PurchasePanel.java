package inventario.ui.componets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PurchasePanel extends JPanel{
    private JTextField tfProveedor, tfNumFactura, tfProducto, tfStock, tfCosto;
    private JButton btnAdd, btnSave;
    private DefaultTableModel model;

    public PurchasePanel() {
        setLayout(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        tfProveedor = new JTextField();
        tfNumFactura = new JTextField();
        tfProducto = new JTextField();
        tfStock = new JTextField();
        tfCosto = new JTextField();
        form.add(new JLabel("Proveedor:")); form.add(tfProveedor);
        form.add(new JLabel("No. Factura:")); form.add(tfNumFactura);
        form.add(new JLabel("Producto (ID/Nombre):")); form.add(tfProducto);
        form.add(new JLabel("Stock Comprado:")); form.add(tfStock);
        form.add(new JLabel("Costo por unidad:")); form.add(tfCosto);

        btnAdd = new JButton("Agregar Producto a Factura");
        btnSave = new JButton("Guardar Compra");
        JPanel btns = new JPanel(); btns.add(btnAdd); btns.add(btnSave);

        model = new DefaultTableModel(new String[]{"ID","Nombre","Cantidad","Costo U."}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(btns, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Listeners
        btnAdd.addActionListener(e -> onAdd());
        btnSave.addActionListener(e -> onSave());
    }

    private void onAdd() {
        // TODO: buscar en BD por ID o nombre
        String id = ""; String nombre = tfProducto.getText();
        int cant = Integer.parseInt(tfStock.getText());
        double costo = Double.parseDouble(tfCosto.getText());
        model.addRow(new Object[]{id, nombre, cant, costo});
    }

    private void onSave() {
        // TODO: guardar factura y actualizar stock
        JOptionPane.showMessageDialog(this, "Compra guardada exitosamente");
        model.setRowCount(0);
    }
}
