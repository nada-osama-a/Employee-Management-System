package gui;

import javax.swing.*;
import java.awt.*;

import model.*;
import Service.EmployeeManager;
import Service.DepartmentManager;
import Validation.Validator;

public class AddEmployeePanel extends JPanel {

    private EmployeeManager employeeManager;
    private DepartmentManager departmentManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField positionField;

    private JComboBox<Department> departmentBox;
    private JComboBox<EmployeeType> typeBox;

    private JTextField basicSalaryField;
    private JTextField bonusField;

    private JTextField hourlyRateField;
    private JTextField hoursWorkedField;

    private JPanel salaryPanel;
    

    public AddEmployeePanel(EmployeeManager employeeManager,
            DepartmentManager departmentManager) {

        this.employeeManager = employeeManager;
        this.departmentManager = departmentManager;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Initialize fields
        idField = new JTextField(15);
        nameField = new JTextField(15);
        ageField = new JTextField(15);
        positionField = new JTextField(15);

        departmentBox = new JComboBox<>();
        typeBox = new JComboBox<>(EmployeeType.values());

        basicSalaryField = new JTextField(15);
        bonusField = new JTextField(15);

        hourlyRateField = new JTextField(15);
        hoursWorkedField = new JTextField(15);

        // Employee Information Section
        JPanel employeeInfoPanel = new JPanel(
                new GridLayout(0, 2, 10, 12));

        employeeInfoPanel.setBorder(
                BorderFactory.createTitledBorder("Employee Information"));

        employeeInfoPanel.add(new JLabel("Employee ID:"));
        employeeInfoPanel.add(idField);

        employeeInfoPanel.add(new JLabel("Name:"));
        employeeInfoPanel.add(nameField);

        employeeInfoPanel.add(new JLabel("Age:"));
        employeeInfoPanel.add(ageField);

        employeeInfoPanel.add(new JLabel("Department:"));
        employeeInfoPanel.add(departmentBox);

        employeeInfoPanel.add(new JLabel("Position:"));
        employeeInfoPanel.add(positionField);

        employeeInfoPanel.add(new JLabel("Employee Type:"));
        employeeInfoPanel.add(typeBox);

        // Salary Information Section
        salaryPanel = new JPanel(
                new GridLayout(0, 2, 10, 12));

        salaryPanel.setBorder(
                BorderFactory.createTitledBorder("Salary Information"));

        // Main Form
        JPanel formPanel = new JPanel(new BorderLayout(15, 15));

        formPanel.add(employeeInfoPanel, BorderLayout.NORTH);
        formPanel.add(salaryPanel, BorderLayout.CENTER);

        add(formPanel, BorderLayout.CENTER);

        // Add Employee Button
        JButton addButton = new JButton("Add Employee");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        typeBox.addActionListener(e -> updateSalaryFields());

        addButton.addActionListener(e -> addEmployee());

        refreshDepartments();
        updateSalaryFields();
    }

    public void refreshDepartments() {

        departmentBox.removeAllItems();

        for (Department d : departmentManager.getAllDepartments()) {
            departmentBox.addItem(d);
        }
    }

    private void updateSalaryFields() {

        salaryPanel.removeAll();

        EmployeeType selectedType = (EmployeeType) typeBox.getSelectedItem();

        if (selectedType == EmployeeType.FULL_TIME) {

            salaryPanel.add(new JLabel("Basic Salary:"));
            salaryPanel.add(basicSalaryField);

            salaryPanel.add(new JLabel("Bonus:"));
            salaryPanel.add(bonusField);

        } else if (selectedType == EmployeeType.PART_TIME) {

            salaryPanel.add(new JLabel("Hourly Rate:"));
            salaryPanel.add(hourlyRateField);

            salaryPanel.add(new JLabel("Hours Worked:"));
            salaryPanel.add(hoursWorkedField);
        }

        salaryPanel.revalidate();
        salaryPanel.repaint();
    }

    private void addEmployee() {

        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String position = positionField.getText();

        Department department = (Department) departmentBox.getSelectedItem();

        EmployeeType type = (EmployeeType) typeBox.getSelectedItem();

        if (!Validator.isValidId(id)
                || !Validator.isValidName(name)
                || !Validator.isValidAge(age)
                || !Validator.isValidPosition(position)) {

            JOptionPane.showMessageDialog(
                    this, "Please enter valid employee information.");

            return;
        }

        if (department == null) {

            JOptionPane.showMessageDialog(
                    this, "Please add a department first.");

            return;
        }

        int employeeId = Integer.parseInt(id.trim());
        int employeeAge = Integer.parseInt(age.trim());

        if (!employeeManager.isAvailableId(employeeId)) {

            JOptionPane.showMessageDialog(
                    this, "Employee ID already exists.");

            return;
        }

        Employee employee;

        try {

            if (type == EmployeeType.FULL_TIME) {

                String basic = basicSalaryField.getText();
                String bonus = bonusField.getText();

                if (!Validator.isValidBasicSalary(basic)
                        || !Validator.isValidBonus(bonus)) {

                    JOptionPane.showMessageDialog(
                            this, "Enter valid salary and bonus.");

                    return;
                }

                employee = new FullTimeEmployee(
                        employeeId,
                        name,
                        employeeAge,
                        department,
                        position,
                        Double.parseDouble(basic),
                        Double.parseDouble(bonus));

            } else {

                String rate = hourlyRateField.getText();
                String hours = hoursWorkedField.getText();

                if (!Validator.isValidHourlyRate(rate)
                        || !Validator.isValidHoursWorked(hours)) {

                    JOptionPane.showMessageDialog(
                            this, "Enter valid hourly rate and hours.");

                    return;
                }

                employee = new PartTimeEmployee(
                        employeeId,
                        name,
                        employeeAge,
                        department,
                        position,
                        Double.parseDouble(rate),
                        Double.parseDouble(hours));
            }

            boolean added = employeeManager.addEmployee(employee);

            if (added) {

                JOptionPane.showMessageDialog(
                        this, "Employee added successfully!");

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this, "Could not save employee. Please try again.");
            }

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        positionField.setText("");

        basicSalaryField.setText("");
        bonusField.setText("");

        hourlyRateField.setText("");
        hoursWorkedField.setText("");
    }
}