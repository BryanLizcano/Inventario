package inventario.ui;

import inventario.model.Producto;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Nombre", "Categoría", "Costo Compra", "Precio Venta", "Stock"};
    private List<Producto> productos = new ArrayList<>();

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = productos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getId();
            case 1 -> p.getNombre();
            case 2 -> p.getCategoria().name();
            case 3 -> p.getCostoCompra();
            case 4 -> p.getPrecioVenta();
            case 5 -> p.getStock();
            default -> null;
        };
    }
}
