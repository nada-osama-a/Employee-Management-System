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
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        AppTheme.stylePanel(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(
                "Employee Management System",
                SwingConstants.CENTER);
        AppTheme.styleTitle(titleLabel);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(AppTheme.FONT_BOLD);

        AddEmployeePanel employeePanel = new AddEmployeePanel(employeeManager, departmentManager);

        DepartmentPanel departmentPanel = new DepartmentPanel(departmentManager, () -> {
            employeePanel.refreshDepartments();
        });

        tabs.addTab("Employees", employeePanel);
        tabs.addTab("Departments", departmentPanel);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(tabs, BorderLayout.CENTER);

        add(mainPanel);
    }
}
