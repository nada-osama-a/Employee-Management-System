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
    private Runnable onEmployeeAdded;

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
    private JButton addButton;

    public AddEmployeePanel(EmployeeManager employeeManager,
            DepartmentManager departmentManager) {

        this.employeeManager = employeeManager;
        this.departmentManager = departmentManager;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        AppTheme.stylePanel(this);

        idField = new JTextField(15);
        nameField = new JTextField(15);
        ageField = new JTextField(15);
        positionField = new JTextField(15);

        departmentBox = new JComboBox<>();
        typeBox = new JComboBox<>(EmployeeType.values());
        typeBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == EmployeeType.FULL_TIME) {
                    setText("Full-Time");
                } else if (value == EmployeeType.PART_TIME) {
                    setText("Part-Time");
                }
                return this;
            }
        });

        basicSalaryField = new JTextField(15);
        bonusField = new JTextField(15);
        hourlyRateField = new JTextField(15);
        hoursWorkedField = new JTextField(15);

        styleInputs();

        JPanel employeeInfoPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        employeeInfoPanel.setOpaque(false);
        employeeInfoPanel.setBorder(AppTheme.titledBorder("Employee Information"));

        employeeInfoPanel.add(labeled("Employee ID:"));
        employeeInfoPanel.add(idField);
        employeeInfoPanel.add(labeled("Name:"));
        employeeInfoPanel.add(nameField);
        employeeInfoPanel.add(labeled("Age:"));
        employeeInfoPanel.add(ageField);
        employeeInfoPanel.add(labeled("Department:"));
        employeeInfoPanel.add(departmentBox);
        employeeInfoPanel.add(labeled("Position:"));
        employeeInfoPanel.add(positionField);
        employeeInfoPanel.add(labeled("Employee Type:"));
        employeeInfoPanel.add(typeBox);

        salaryPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        salaryPanel.setOpaque(false);
        salaryPanel.setBorder(AppTheme.titledBorder("Salary Information"));

        JPanel formPanel = new JPanel(new BorderLayout(15, 15));
        formPanel.setOpaque(false);
        formPanel.add(employeeInfoPanel, BorderLayout.NORTH);
        formPanel.add(salaryPanel, BorderLayout.CENTER);

        addButton = new JButton("Add Employee");
        AppTheme.stylePrimaryButton(addButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        typeBox.addActionListener(e -> updateSalaryFields());
        addButton.addActionListener(e -> addEmployee());

        refreshDepartments();
        updateSalaryFields();
    }

    public void setOnEmployeeAdded(Runnable onEmployeeAdded) {
        this.onEmployeeAdded = onEmployeeAdded;
    }

    private void styleInputs() {
        AppTheme.styleField(idField);
        AppTheme.styleField(nameField);
        AppTheme.styleField(ageField);
        AppTheme.styleField(positionField);
        AppTheme.styleField(basicSalaryField);
        AppTheme.styleField(bonusField);
        AppTheme.styleField(hourlyRateField);
        AppTheme.styleField(hoursWorkedField);
        AppTheme.styleCombo(departmentBox);
        AppTheme.styleCombo(typeBox);
    }

    private JLabel labeled(String text) {
        JLabel label = new JLabel(text);
        AppTheme.styleLabel(label);
        return label;
    }

    public void refreshDepartments() {
        Department selected = (Department) departmentBox.getSelectedItem();
        departmentBox.removeAllItems();
        for (Department d : departmentManager.getAllDepartments()) {
            departmentBox.addItem(d);
        }
        if (selected != null) {
            for (int i = 0; i < departmentBox.getItemCount(); i++) {
                Department item = departmentBox.getItemAt(i);
                if (item.getDepartmentId() == selected.getDepartmentId()) {
                    departmentBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void updateSalaryFields() {
        salaryPanel.removeAll();
        EmployeeType selectedType = (EmployeeType) typeBox.getSelectedItem();

        if (selectedType == EmployeeType.FULL_TIME) {
            salaryPanel.add(labeled("Basic Salary:"));
            salaryPanel.add(basicSalaryField);
            salaryPanel.add(labeled("Bonus:"));
            salaryPanel.add(bonusField);
        } else if (selectedType == EmployeeType.PART_TIME) {
            salaryPanel.add(labeled("Hourly Rate:"));
            salaryPanel.add(hourlyRateField);
            salaryPanel.add(labeled("Hours Worked:"));
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
                        name.trim(),
                        employeeAge,
                        department,
                        position.trim(),
                        Double.parseDouble(basic.trim()),
                        Double.parseDouble(bonus.trim()));
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
                        name.trim(),
                        employeeAge,
                        department,
                        position.trim(),
                        Double.parseDouble(rate.trim()),
                        Double.parseDouble(hours.trim()));
            }

            boolean added = employeeManager.addEmployee(employee);

            if (added) {
                JOptionPane.showMessageDialog(
                        this, "Employee added successfully!");
                clearFields();
                if (onEmployeeAdded != null) {
                    onEmployeeAdded.run();
                }
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
