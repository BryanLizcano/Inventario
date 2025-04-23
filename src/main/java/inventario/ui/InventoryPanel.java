package inventario.ui;

import inventario.controller.MainController;
import inventario.model.Categoria;
import inventario.model.Producto;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class InventoryPanel extends JPanel {
    private final MainController controller;
    private final JTable table;
    private final ProductoTableModel model;

    public InventoryPanel(MainController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        model = new ProductoTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnRefresh = new JButton("Refrescar");
        btnRefresh.addActionListener(e -> loadData());
        add(btnRefresh, BorderLayout.SOUTH);
        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(e -> mostrarFormularioAgregarProducto());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRefresh);
        panelBotones.add(btnAgregar);

        add(panelBotones, BorderLayout.SOUTH);


        loadData();
    }

    private void loadData() {
        try {
            List<Producto> lista = controller.inventarioService.listarTodos();
            model.setProductos(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public Categoria obtenerCategoriaPorNombre(String nombre) {
        try {
            return Categoria.valueOf(nombre.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Si no se encuentra la categoría, puedes crear una nueva o manejar el error según sea necesario
            return Categoria.DEFAULT;
        }
    }


    private void mostrarFormularioAgregarProducto() {
        JTextField nombreField = new JTextField(15);
        JComboBox<Categoria> categoriaComboBox = new JComboBox<>(Categoria.values());
        JTextField costoCompraField = new JTextField(10);
        JTextField precioVentaField = new JTextField(10);
        JTextField stockField = new JTextField(5);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaComboBox);
        panel.add(new JLabel("Costo Compra:"));
        panel.add(costoCompraField);
        panel.add(new JLabel("Precio Venta:"));
        panel.add(precioVentaField);
        panel.add(new JLabel("Stock:"));
        panel.add(stockField);

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


}
