package inventario.ui.swing;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ModernJLabel extends JLabel {
    private static final long serialVersionUID = 1L;  // Necesario para NetBeans

    public ModernJLabel() {
        this(""); // Constructor sin texto para el diseñador
    }

    public ModernJLabel(String text) {
        super(text);
        configureAppearance();
    }

    private void configureAppearance() {
        // Fuente y color base
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(new Color(60, 60, 60)); // Gris oscuro
        
        // Padding y alineación
        setBorder(new EmptyBorder(5, 10, 5, 10)); // Top, left, bottom, right
        
        // Configuración opcional para usos especiales
        setOpaque(false);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    // Versión con énfasis (bold)
    public ModernJLabel(String text, boolean emphasized) {
        this(text);
        if(emphasized) {
            setFont(getFont().deriveFont(Font.BOLD));
            setForeground(new Color(40, 40, 40));
        }
    }

    // Versión con color personalizado
    public ModernJLabel(String text, Color color) {
        this(text);
        setForeground(color);
    }
}
