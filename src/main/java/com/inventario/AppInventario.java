package com.inventario;

import com.inventario.ui.MainFrame;

import javax.swing.*;

public class AppInventario {
    public static void main(String[] args) {
        // Inicialización temprana de recursos
        DatabaseInitializer.initialize();
        ServicesInitializer.configure();

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
