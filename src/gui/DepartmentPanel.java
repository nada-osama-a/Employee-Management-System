package gui;

import javax.swing.*;
import java.awt.*;

import model.Department;
import Service.DepartmentManager;
import Validation.Validator;

public class DepartmentPanel extends JPanel {

    private DepartmentManager departmentManager;
    private Runnable onDepartmentsChanged;

    private JTextField idField;
    private JTextField nameField;
    private JTextArea departmentsArea;

    public DepartmentPanel(DepartmentManager departmentManager,
            Runnable onDepartmentsChanged) {
        this.departmentManager = departmentManager;
        this.onDepartmentsChanged = onDepartmentsChanged;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        AppTheme.stylePanel(this);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(AppTheme.titledBorder("Department Information"));

        idField = new JTextField(15);
        nameField = new JTextField(15);
        AppTheme.styleField(idField);
        AppTheme.styleField(nameField);

        JLabel idLabel = new JLabel("Department ID:");
        JLabel nameLabel = new JLabel("Department Name:");
        AppTheme.styleLabel(idLabel);
        AppTheme.styleLabel(nameLabel);

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        departmentsArea = new JTextArea();
        departmentsArea.setEditable(false);
        AppTheme.styleTextArea(departmentsArea);

        JScrollPane scrollPane = new JScrollPane(departmentsArea);
        scrollPane.setBorder(AppTheme.titledBorder("Departments List"));
        scrollPane.getViewport().setBackground(AppTheme.CARD);

        JButton addButton = new JButton("Add Department");
        AppTheme.stylePrimaryButton(addButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addDepartment());
        updateDepartmentsList();
    }

    private void addDepartment() {
        String id = idField.getText();
        String name = nameField.getText();

        if (!Validator.isValidId(id) || !Validator.isValidDepartment(name)) {
            JOptionPane.showMessageDialog(
                    this, "Please enter valid department information.");
            return;
        }

        try {
            int departmentId = Integer.parseInt(id.trim());

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

            if (!departmentManager.addDepartment(department)) {
                JOptionPane.showMessageDialog(this,
                        "Could not add the department (duplicate or file error).");
                return;
            }
            JOptionPane.showMessageDialog(this, "Department added successfully!");

            idField.setText("");
            nameField.setText("");

            updateDepartmentsList();

            if (onDepartmentsChanged != null) {
                onDepartmentsChanged.run();
            }

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
                            + "    |    Name: " + d.getDepartmentName()
                            + "\n");
        }
        if (departmentManager.getAllDepartments().isEmpty()) {
            departmentsArea.setText("No departments yet. Add one using the form above.");
        }
    }
}
