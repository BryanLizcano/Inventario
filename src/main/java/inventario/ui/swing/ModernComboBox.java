package inventario.ui.swing;

import javax.swing.*;
import java.awt.*;

public class ModernComboBox extends JComboBox<String> {
    public ModernComboBox() {
        configureAppearance();
    }

    private void configureAppearance() {
        setRenderer(new ModernComboRenderer());
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        setBackground(Color.WHITE);
        setForeground(Color.DARK_GRAY);
    }

    private class ModernComboRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            label.setBackground(isSelected ?
                    new Color(220, 240, 255) : Color.WHITE);

            return label;
        }
    }
}
