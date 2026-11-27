package gui;

import javax.swing.*;
import java.awt.*;

import model.Department;
import Service.DepartmentManager;

public class DepartmentPanel extends JPanel {

    private DepartmentManager departmentManager;
    private AddEmployeePanel employeePanel;

    private JTextField idField;
    private JTextField nameField;

    private JTextArea departmentsArea;

    public DepartmentPanel(DepartmentManager departmentManager,
            AddEmployeePanel employeePanel) {
        System.out.println("DepartmentPanel Constructor Started");
        this.departmentManager = departmentManager;
        this.employeePanel = employeePanel;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Department Information Section
        JPanel inputPanel = new JPanel(
                new GridLayout(2, 2, 10, 12));

        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Department Information"));

        idField = new JTextField(15);
        nameField = new JTextField(15);

        inputPanel.add(new JLabel("Department ID:"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Department Name:"));
        inputPanel.add(nameField);

        // Departments List Section
        departmentsArea = new JTextArea();
        departmentsArea.setEditable(false);
        departmentsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        departmentsArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(departmentsArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Departments List"));

        // Add Department Button
        JButton addButton = new JButton("Add Department");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        // Add Components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Action
        addButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Button Clicked!");
            addDepartment();
        });

        updateDepartmentsList();
    }

    private void addDepartment() {

        String id = idField.getText();
        String name = nameField.getText();

        try {

            int departmentId = Integer.parseInt(id.trim());

            if (departmentId <= 0 || name.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this, "Please enter valid department information.");

                return;
            }

            if (!departmentManager.isAvailableId(departmentId)) {

                JOptionPane.showMessageDialog(
                        this, "Department ID already exists.");

                return;
            }

            if (!departmentManager.isAvailableName(name)) {

                JOptionPane.showMessageDialog(
                        this, "Department name already exists.");

                return;
            }

            Department department = new Department(departmentId, name);

            departmentManager.addDepartment(department);

            JOptionPane.showMessageDialog(
                    this, "Department added successfully!");

            idField.setText("");
            nameField.setText("");

            updateDepartmentsList();

            employeePanel.refreshDepartments();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this, "Department ID must be a number.");

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void updateDepartmentsList() {

        departmentsArea.setText("");

        for (Department d : departmentManager.getAllDepartments()) {

            departmentsArea.append(
                    "ID: " + d.getDepartmentId()
                            + " | Name: " + d.getDepartmentName()
                            + "\n");
        }
    }
}