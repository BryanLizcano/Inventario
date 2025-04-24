package inventario.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModernTextField extends JTextField {
    public ModernTextField() {
        configureAppearance();
    }

    private void configureAppearance() {
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(Color.DARK_GRAY);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        setBackground(Color.WHITE);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(70, 130, 180)),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                ));
            }
        });
    }
}
