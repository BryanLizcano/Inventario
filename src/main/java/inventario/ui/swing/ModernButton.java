package inventario.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {
    // Mantén esta línea para que NetBeans lo detecte como componente bean
    private static final long serialVersionUID = 1L;

    private final Color hoverColor = new Color(60, 110, 160);
    private final Color pressedColor = new Color(40, 90, 140);

    // Constructor sin argumentos necesario para el diseñador
    public ModernButton() {
        this(""); // Llama al constructor con texto vacío
    }

    public ModernButton(String text) {
        super(text);
        configureAppearance();
        addHoverEffects();
    }

    private void configureAppearance() {
        setBackground(new Color(70, 130, 180));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void addHoverEffects() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(70, 130, 180));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor);
            }
        });
    }
}