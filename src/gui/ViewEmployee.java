package gui;

import Service.*;
import model.*;

import java.util.List;

import javax.swing.*;

public class ViewEmployee extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewEmployee.class.getName());
    private Employee employee;
    private final EmployeeManager employeeManager;                     
    private final DepartmentManager departmentManager;
    private final Runnable onUpdated;
  

    public ViewEmployee(EmployeeManager employeeManager, DepartmentManager departmentManager,
            Employee employee, Runnable onUpdated) {
        this.employeeManager = employeeManager;
        this.departmentManager = departmentManager;
        this.employee = employee;
        this.onUpdated = onUpdated;
        initComponents();
        applyTheme();
        loadEmployee();
        setLocationRelativeTo(null);
    }

    private void applyTheme() {
        getContentPane().setBackground(AppTheme.BACKGROUND);
        AppTheme.styleTitle(employeeName);
        AppTheme.styleMutedLabel(positionDepartment);
        AppTheme.styleLabel(jLabel2);
        AppTheme.styleLabel(jLabel5);
        AppTheme.styleLabel(jLabel6);
        AppTheme.styleLabel(jLabel7);
        AppTheme.styleSubtitle(jLabel8);
        jLabel8.setText("Employee details");
        jLabel8.setOpaque(false);
        AppTheme.styleReadOnlyValue(id2);
        AppTheme.styleReadOnlyValue(age1);
        AppTheme.styleReadOnlyValue(jLabel4);
        AppTheme.styleReadOnlyValue(salary);
        AppTheme.styleReadOnlyValue(jLabel3);
        id1.setVisible(false);
        jLabel1.setText("");
        AppTheme.stylePrimaryButton(update);
        AppTheme.styleDangerButton(delete);
        jSeparator1.setForeground(AppTheme.BORDER);
        jSeparator1.setBackground(AppTheme.BORDER);
    }
    private void loadEmployee() {
        if (employee == null) {
            JOptionPane.showMessageDialog(this, "No employee to display.");
            dispose();
            return;
        }

        employeeName.setText(employee.getName());
        positionDepartment.setText(employee.getPosition() + "  |  "
                + employee.getDepartment().getDepartmentName());
        id2.setText(String.valueOf(employee.getId()));
        age1.setText(String.valueOf(employee.getAge()));
        salary.setText(String.format("%.2f EGP", employee.calculateSalary()));

        String typeText = employee.getType() == EmployeeType.FULL_TIME ? "Full-Time" : "Part-Time";
        jLabel4.setText(typeText);

        String details;
        if (employee instanceof FullTimeEmployee) {
            FullTimeEmployee f = (FullTimeEmployee) employee;
            details = String.format("Basic salary: %.2f     Bonus: %.2f", f.getBasicSalary(), f.getBonus());
        } else if (employee instanceof PartTimeEmployee) {
            PartTimeEmployee p = (PartTimeEmployee) employee;
            details = String.format("Hourly rate: %.2f     Hours: %.2f", p.getHourlyRate(), p.getHoursWorked());
        } else {
            details = "";
        }
        jLabel3.setText(details);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jLabel1 = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        positionDepartment = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        delete = new javax.swing.JToggleButton();
        update = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        salary = new javax.swing.JLabel();
        id1 = new javax.swing.JLabel();
        id2 = new javax.swing.JLabel();
        age1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-profilee.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        employeeName.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        employeeName.setText("employeeName");

        positionDepartment.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        positionDepartment.setText("position  department");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setOpaque(true);

        delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete.setText("Delete");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));
        delete.setBorderPainted(false);
        delete.setOpaque(true);
        delete.addActionListener(this::deleteActionPerformed);

        update.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        update.setText("Update");
        update.setBorder(null);
        update.setOpaque(true);
        update.addActionListener(this::updateActionPerformed);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("ID:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("type");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Age:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Salary:");

        salary.setBackground(new java.awt.Color(255, 255, 255));
        salary.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        salary.setText("salary");
        salary.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        salary.setOpaque(true);

        id1.setBackground(new java.awt.Color(255, 255, 255));
        id1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        id1.setText("ID");
        id1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        id1.setOpaque(true);

        id2.setBackground(new java.awt.Color(255, 255, 255));
        id2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        id2.setText("ID");
        id2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        id2.setOpaque(true);

        age1.setBackground(new java.awt.Color(255, 255, 255));
        age1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        age1.setText("age");
        age1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        age1.setOpaque(true);

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Total:");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Employee detials:");
        jLabel8.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(31, 31, 31)
                                        .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(positionDepartment))
                                            .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(22, 22, 22)
                                        .addComponent(age1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)))))
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(85, 85, 85)
                    .addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(494, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(employeeName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(positionDepartment))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(age1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(262, 262, 262)
                    .addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(266, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {                                       
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + employee.getName() + "?",
                "Confirm delete", JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) {
            delete.setSelected(false);
            return;
        }

        if (employeeManager.deleteEmployee(employee.getId())) {
            JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            if (onUpdated != null)
                onUpdated.run();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Could not delete the employee (not found or file error).");
            delete.setSelected(false);
        }

    }

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {
        new UpdatePanel(
                employeeManager,
                departmentManager,
                employee,
                () -> {
                    Employee fresh = employeeManager.searchById(employee.getId());
                    if (fresh != null) {
                        employee = fresh;
                        loadEmployee();
                    }
                    if (onUpdated != null) {
                        onUpdated.run();
                    }
                }).setVisible(true);
        update.setSelected(false); 

    }
    private javax.swing.JLabel age1;
    private javax.swing.JToggleButton delete;
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel id1;
    private javax.swing.JLabel id2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JLabel positionDepartment;
    private javax.swing.JLabel salary;
    private javax.swing.JToggleButton update;
    // End of variables declaration//GEN-END:variables
}