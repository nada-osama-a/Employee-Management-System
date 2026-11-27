package gui;

import Service.DepartmentManager;
import Service.EmployeeManager;
import model.Department;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

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
    private final List<Department> deps = departmentManager.getAllDepartments();
    private final AddEmployeePanel addEmployeePanel = new AddEmployeePanel(employeeManager, departmentManager);

    public DashboardFrame(String username) {
        this.username = username;
        setTitle("Employee Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildHeader(), BorderLayout.NORTH);
        add(buildSidebar(), BorderLayout.WEST);
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
        header.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        JLabel title = new JLabel("Employee Management System");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        JLabel user = new JLabel("Logged in as: " + username);
        header.add(title, BorderLayout.WEST);
        header.add(user, BorderLayout.EAST);
        return header;
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebar.setPreferredSize(new Dimension(190, 0));

        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());

        sidebar.add(navPanel, BorderLayout.NORTH);
        sidebar.add(logoutButton, BorderLayout.SOUTH);
        return sidebar;
    }

    public void addPage(String title, JFrame frame) {
        JPanel wrapper = new JPanel(new java.awt.BorderLayout());
        wrapper.add(frame.getContentPane(), java.awt.BorderLayout.CENTER);
        addPage(title, wrapper);
    }
    private void registerPages() {
        addPage("Home", buildHomePanel());
        addPage("Add Employee", new AddEmployeePanel(employeeManager, departmentManager));
        addPage("Employees", new SearchPanel(employeeManager, deps));   // JPanel or JFrame both work now
        addPage("Departments", new DepartmentPanel(departmentManager, addEmployeePanel));
    }

    private void addPage(String title, JPanel panel) {
        contentPanel.add(panel, title);
        JButton button = new JButton(title);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.addActionListener(e -> showPage(title));
        navPanel.add(button);
        navPanel.add(Box.createVerticalStrut(8));
    }

    private void showPage(String title) {
        cardLayout.show(contentPanel, title);
    }

    private JPanel buildHomePanel() {
        JPanel home = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new java.awt.Insets(8, 8, 8, 8);

        JLabel welcome = new JLabel("Welcome, " + username + "!");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridy = 0;
        home.add(welcome, gbc);

        JLabel stats = new JLabel("Employees: " + employeeManager.getAllEmployees().size()
                + "   |   Departments: " + departmentManager.getAllDepartments().size());
        stats.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy = 1;
        home.add(stats, gbc);
        return home;
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
