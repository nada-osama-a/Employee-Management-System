package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlaceholderPanel extends JPanel {
    public PlaceholderPanel(String title) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel(title + " (coming soon)", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(label, BorderLayout.CENTER);
    }
}
