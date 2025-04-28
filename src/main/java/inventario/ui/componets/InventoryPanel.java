package inventario.ui.componets;

import inventario.model.Producto;
import inventario.ui.swing.ModernButton;
import inventario.ui.swing.ModernComboBox;
import inventario.ui.swing.ModernTable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class InventoryPanel extends JPanel {
    private JTextField tfSearch;
    private JComboBox<String> cbFilter;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnAdd, btnModify, btnDisable;

    public InventoryPanel() {
        initComponents();
        initListeners();
        loadAllProducts();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Top search panel
        JPanel top = new JPanel(new BorderLayout(5, 5));
        tfSearch = new JTextField();
        cbFilter = new JComboBox<>(new String[]{"ID", "Nombre"});
        top.add(new JLabel("Buscar por:"), BorderLayout.WEST);
        top.add(cbFilter, BorderLayout.CENTER);
        top.add(tfSearch, BorderLayout.EAST);

        // Table setup
        String[] cols = {"ID", "Nombre", "Costo", "Precio Venta", "Stock"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new JButton("Agregar");
        btnModify = new JButton("Modificar");
        btnDisable = new JButton("Inhabilitar");
        btnPanel.add(btnAdd);
        btnPanel.add(btnModify);
        btnPanel.add(btnDisable);

        // Assemble
        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void initListeners() {
        // Dynamic filter
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }
        });

        btnAdd.addActionListener(e -> onAdd());
        btnModify.addActionListener(e -> onModify());
        btnDisable.addActionListener(e -> onDisable());
    }

    private void loadAllProducts() {
        model.setRowCount(0);
        List<Producto> products = ProductDAO.getAll();
        for (Producto p : products) {
            model.addRow(new Object[]{p.getId(), p.getNombre(), p.getCostoCompra(), p.getPrecioVenta(), p.getStock()});
        }
    }

    private void filter() {
        String query = tfSearch.getText().trim();
        String by = (String) cbFilter.getSelectedItem();
        model.setRowCount(0);
        List<Producto> results = ProductDAO.search(by.toLowerCase(), query);
        for (Producto p : results) {
            model.addRow(new Object[]{p.getId(), p.getNombre(), p.getCostoCompra(), p.getPrecioVenta(), p.getStock()});
        }
    }

    private void onAdd() {
        ProductDialog dialog = new ProduPctDialog(null);
        dialog.setVisible(true);
        if (dialog.isSaved()) loadAllProducts();
    }

    private void onModify() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        Product existing = ProductDAO.getById(id);
        ProductDialog dialog = new ProductDialog(existing);
        dialog.setVisible(true);
        if (dialog.isSaved()) loadAllProducts();
    }

    private void onDisable() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para inhabilitar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Inhabilitar el producto seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ProductoDao.desabilitar(id);
            loadAllProducts();
        }
    }
}
