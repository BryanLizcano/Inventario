package inventario.ui.componets;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesPanel extends JPanel{
    private JTextField tfProducto, tfCantidad;
    private JComboBox<String> cbClientes;
    private JButton btnAdd, btnFinalize;
    private DefaultTableModel model;

    public SalesPanel() {
        setLayout(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(3,2,5,5));
        tfProducto = new JTextField();
        tfCantidad = new JTextField();
        cbClientes = new JComboBox<>(loadClientes());
        form.add(new JLabel("Producto (ID/Nombre):")); form.add(tfProducto);
        form.add(new JLabel("Cantidad Vendida:")); form.add(tfCantidad);
        form.add(new JLabel("Cliente:")); form.add(cbClientes);

        btnAdd = new JButton("Agregar a Venta");
        btnFinalize = new JButton("Finalizar Venta");
        JPanel btns = new JPanel(); btns.add(btnAdd); btns.add(btnFinalize);

        model = new DefaultTableModel(new String[]{"ID","Nombre","Cantidad","Cliente"}, 0);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(btns, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> onAdd());
        btnFinalize.addActionListener(e -> onFinalize());
    }

    private String[] loadClientes() {
        // TODO: cargar desde BD
        return new String[]{"Cliente Genérico","Usuario1","Usuario2"};
    }

    private void onAdd() {
        String id=""; String nombre=tfProducto.getText();
        int cant=Integer.parseInt(tfCantidad.getText());
        String cliente=(String)cbClientes.getSelectedItem();
        model.addRow(new Object[]{id,nombre,cant,cliente});
    }

    private void onFinalize() {
        // TODO: actualizar stock y registrar venta
        JOptionPane.showMessageDialog(this, "Venta realizada exitosamente");
        model.setRowCount(0);
    }
}
