package gui;

import Service.AuthService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginFrame extends JFrame {
    private final JTextField usernameField = new JTextField(16);
    private final JPasswordField passwordField = new JPasswordField(16);
    private final JButton loginButton = new JButton("Login");
    private final JButton exitButton = new JButton("Exit");
    private final AuthService authService = new AuthService();

    public LoginFrame() {
        setTitle("Employee Management System - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 340);
        setLocationRelativeTo(null);
        setResizable(false);
        buildUi();
        registerListeners();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(AppTheme.PRIMARY);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppTheme.PRIMARY);
        header.setBorder(BorderFactory.createEmptyBorder(22, 24, 18, 24));
        JLabel title = new JLabel("Employee Management System", SwingConstants.CENTER);
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(Color.WHITE);
        JLabel subtitle = new JLabel("Sign in to continue", SwingConstants.CENTER);
        subtitle.setFont(AppTheme.FONT_SMALL);
        subtitle.setForeground(new Color(210, 220, 235));
        header.add(title, BorderLayout.CENTER);
        header.add(subtitle, BorderLayout.SOUTH);

        JPanel card = new JPanel(new GridBagLayout());
        AppTheme.styleCard(card);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel userLabel = new JLabel("Username");
        AppTheme.styleLabel(userLabel);
        JLabel passLabel = new JLabel("Password");
        AppTheme.styleLabel(passLabel);
        AppTheme.styleField(usernameField);
        AppTheme.styleField(passwordField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        card.add(userLabel, gbc);
        gbc.gridy = 1;
        card.add(usernameField, gbc);
        gbc.gridy = 2;
        card.add(passLabel, gbc);
        gbc.gridy = 3;
        card.add(passwordField, gbc);

        AppTheme.stylePrimaryButton(loginButton);
        AppTheme.styleSecondaryButton(exitButton);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        buttons.setOpaque(false);
        buttons.add(loginButton);
        buttons.add(exitButton);
        gbc.gridy = 4;
        gbc.insets = new Insets(16, 8, 4, 8);
        card.add(buttons, gbc);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(AppTheme.BACKGROUND);
        body.setBorder(BorderFactory.createEmptyBorder(20, 28, 24, 28));
        body.add(card, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(body, BorderLayout.CENTER);
        setContentPane(root);
        getRootPane().setDefaultButton(loginButton);
    }

    private void registerListeners() {
        loginButton.addActionListener(e -> handleLogin());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (authService.authenticate(username, password)) {
            dispose();
            new DashboardFrame(username).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }
}
