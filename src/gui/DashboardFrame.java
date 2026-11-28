package gui;

import Service.DepartmentManager;
import Service.EmployeeManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DashboardFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);
    private final JPanel navPanel = new JPanel();
    private final String username;
    private final EmployeeManager employeeManager = new EmployeeManager();
    private final DepartmentManager departmentManager = new DepartmentManager();
    private final AddEmployeePanel addEmployeePanel;
    private final SearchPanel searchPanel;
    private JLabel stats;

    public DashboardFrame(String username) {
        this.username = username;
        this.addEmployeePanel = new AddEmployeePanel(employeeManager, departmentManager);
        this.searchPanel = new SearchPanel(employeeManager, departmentManager);

        addEmployeePanel.setOnEmployeeAdded(() -> {
            refreshStats();
            searchPanel.reloadResults();
        });

        setTitle("Employee Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setMinimumSize(new Dimension(900, 560));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(AppTheme.BACKGROUND);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildSidebar(), BorderLayout.WEST);
        contentPanel.setBackground(AppTheme.BACKGROUND);
        add(contentPanel, BorderLayout.CENTER);

        registerPages();
        showPage("Home");
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }

    public DepartmentManager getDepartmentManager() {
        return departmentManager;
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AppTheme.PRIMARY);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("Employee Management System");
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(Color.WHITE);
        JLabel user = new JLabel("Logged in as: " + username);
        user.setFont(AppTheme.FONT_SMALL);
        user.setForeground(new Color(210, 220, 235));
        header.add(title, BorderLayout.WEST);
        header.add(user, BorderLayout.EAST);
        return header;
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(AppTheme.PRIMARY_DARK);
        sidebar.setBorder(BorderFactory.createEmptyBorder(16, 12, 16, 12));
        sidebar.setPreferredSize(new Dimension(200, 0));

        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setOpaque(false);

        JButton logoutButton = new JButton("Logout");
        AppTheme.styleSecondaryButton(logoutButton);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        logoutButton.addActionListener(e -> logout());

        sidebar.add(navPanel, BorderLayout.NORTH);
        sidebar.add(logoutButton, BorderLayout.SOUTH);
        return sidebar;
    }

    public void addPage(String title, JFrame frame) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(AppTheme.BACKGROUND);
        wrapper.add(frame.getContentPane(), BorderLayout.CENTER);
        addPage(title, wrapper);
    }

    private void registerPages() {
        addPage("Home", buildHomePanel());
        addPage("Add Employee", addEmployeePanel);
        addPage("Employees", searchPanel);
        addPage("Add & show Departments", new DepartmentPanel(departmentManager, () -> {
            addEmployeePanel.refreshDepartments();
            searchPanel.refreshDepartments();
            refreshStats();
        }));
    }

    private void addPage(String title, JPanel panel) {
        panel.setBackground(AppTheme.BACKGROUND);
        contentPanel.add(panel, title);
        JButton button = new JButton(title);
        AppTheme.styleNavButton(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> showPage(title));
        navPanel.add(button);
        navPanel.add(Box.createVerticalStrut(10));
    }

    private void showPage(String title) {
        if ("Home".equals(title)) {
            refreshStats();
        }
        if ("Employees".equals(title)) {
            searchPanel.refreshDepartments();
            searchPanel.reloadResults();
        }
        if ("Add Employee".equals(title)) {
            addEmployeePanel.refreshDepartments();
        }
        cardLayout.show(contentPanel, title);
    }

    private JPanel buildHomePanel() {
        JPanel home = new JPanel(new GridBagLayout());
        AppTheme.stylePanel(home);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new java.awt.Insets(8, 8, 8, 8);

        JLabel welcome = new JLabel("Welcome, " + username + "!");
        welcome.setFont(AppTheme.FONT_LARGE);
        welcome.setForeground(AppTheme.PRIMARY);
        gbc.gridy = 0;
        home.add(welcome, gbc);

        stats = new JLabel();
        AppTheme.styleMutedLabel(stats);
        stats.setFont(AppTheme.FONT_SUBTITLE);
        refreshStats();
        gbc.gridy = 1;
        home.add(stats, gbc);

        JLabel hint = new JLabel("Use the menu on the left to add, search, and manage records.");
        AppTheme.styleMutedLabel(hint);
        gbc.gridy = 2;
        home.add(hint, gbc);
        return home;
    }

    public void refreshStats() {
        if (stats == null) {
            return;
        }
        stats.setText("Employees: " + employeeManager.getAllEmployees().size()
                + "   |   Departments: " + departmentManager.getAllDepartments().size());
    }

    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?",
                "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame().setVisible(true);
        }
    }
}
