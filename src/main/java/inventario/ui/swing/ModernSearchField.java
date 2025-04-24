package inventario.ui.swing;

import javax.swing.*;
import java.awt.*;

public class ModernSearchField extends JTextField {
    public ModernSearchField() {
        configureAppearance();
    }

    private void configureAppearance() {
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(Color.DARK_GRAY);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 35, 8, 10)
        ));
        putClientProperty("JTextField.placeholderText", "Buscar...");
        setOpaque(false);
        setBackground(new Color(250, 250, 250));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Icono de lupa
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(150, 150, 150));
        g2.fillOval(10, 10, 14, 14);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(22, 22, 30, 30);
        g2.dispose();
    }
}