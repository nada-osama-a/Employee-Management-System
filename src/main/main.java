package main;

import gui.AppTheme;
import gui.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        
        AppTheme.installLookAndFeelDefaults();
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}