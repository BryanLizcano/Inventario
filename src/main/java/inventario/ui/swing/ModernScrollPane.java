package inventario.ui.swing;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollPane extends JScrollPane {
    public ModernScrollPane(Component view) {
        super(view);
        configureAppearance();
    }

    private void configureAppearance() {
        setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
        getVerticalScrollBar().setUI(new ModernScrollBarUI());
        getHorizontalScrollBar().setUI(new ModernScrollBarUI());
        setOpaque(false);
        getViewport().setBackground(Color.WHITE);
    }

    private class ModernScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(200, 200, 200);
            this.trackColor = new Color(240, 240, 240);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(thumbColor);
            g2.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2,
                    thumbBounds.width - 4, thumbBounds.height - 4, 6, 6);
            g2.dispose();
        }
    }
}
