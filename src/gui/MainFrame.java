package gui;

import javax.swing.*;
import java.awt.*;

import Service.EmployeeManager;
import Service.DepartmentManager;

public class MainFrame extends JFrame {

    private EmployeeManager employeeManager;
    private DepartmentManager departmentManager;

    public MainFrame() {

        employeeManager = new EmployeeManager();
        departmentManager = new DepartmentManager();

        setTitle("Employee Management System");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Application Title
        JLabel titleLabel = new JLabel(
                "Employee Management System",
                SwingConstants.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 15, 10));

        // Tabs
        JTabbedPane tabs = new JTabbedPane();

        tabs.setFont(new Font("Arial", Font.BOLD, 14));

        AddEmployeePanel employeePanel = new AddEmployeePanel(employeeManager, departmentManager);

        DepartmentPanel departmentPanel = new DepartmentPanel(departmentManager, () -> {
            employeePanel.refreshDepartments();
        });

        tabs.addTab("Employees", employeePanel);
        tabs.addTab("Departments", departmentPanel);

        // Add Components
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(tabs, BorderLayout.CENTER);

        add(mainPanel);
    }
}