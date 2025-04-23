package inventario.ui.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ModernTable extends JTable {
    private int hoverRow = -1;

    public ModernTable() {
        configureAppearance();
        addHoverEffect();
        configureDefaultRenderer(); // Nueva configuración
    }

    private void configureAppearance() {
        // Configuración del encabezado
        JTableHeader header = this.getTableHeader();
        header.setDefaultRenderer(new ModernHeaderRenderer());
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        header.setReorderingAllowed(false);

        // Configuración general de la tabla
        this.setRowHeight(30);
        this.setShowHorizontalLines(true);
        this.setShowVerticalLines(false);
        this.setGridColor(new Color(240, 240, 240));
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setSelectionBackground(new Color(220, 240, 255));
        this.setSelectionForeground(Color.BLACK);
        this.setIntercellSpacing(new Dimension(0, 0));
        this.setFillsViewportHeight(true);
    }

    private void addHoverEffect() {
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = rowAtPoint(evt.getPoint());
                if (row != hoverRow) {
                    hoverRow = row;
                    repaint();
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                hoverRow = -1;
                repaint();
            }
        });
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        // Renderizado personalizado
        if (c instanceof JComponent) {
            JComponent component = (JComponent) c;
            component.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        }

        // Color de fondo
        if (isRowSelected(row)) {
            c.setBackground(getSelectionBackground());
            c.setForeground(getSelectionForeground());
        } else {
            Color background = (row % 2 == 0) ?
                    new Color(250, 250, 250) : Color.WHITE;

            if (row == hoverRow) {
                background = background.brighter();
            }

            c.setBackground(background);
            c.setForeground(Color.DARK_GRAY);
        }

        // Alineación del contenido
        if (c instanceof JLabel) {
            JLabel label = (JLabel) c;
            label.setHorizontalAlignment(
                    getColumnClass(column) == Number.class ?
                            JLabel.RIGHT : JLabel.LEFT
            );
        }

        return c;
    }

    private class ModernHeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);

            setBackground(new Color(70, 130, 180));
            setForeground(Color.WHITE);
            setFont(getFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            setHorizontalAlignment(JLabel.CENTER);

            return this;
        }
    }

    private void configureDefaultRenderer() {
        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                return c;
            }
        });
    }

}