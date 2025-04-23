package inventario.ui;

import inventario.controller.MainController;
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
}
