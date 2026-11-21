/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.ArrayList;
import java.util.List;
import model.Employee;
import model.Department;
import model.EmployeeType;
import model.FullTimeEmployee;
import model.PartTimeEmployee;

/**
 *
 * @author Yomna Mohamed
 */
public class EmployeeManager {

    private ArrayList<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    public boolean isAvailableId(int id) {
        for (Employee emp : employees) {
            if (id == emp.getId()) {
                return false;
            }
        }
        return true;
    }

    public boolean addEmployee(Employee emp) {
        if (emp == null) {
            return false;
        }

        if (isAvailableId(emp.getId())) {
            employees.add(emp);
            return true;
        }

        return false;
    }

    public Employee searchById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> filterByDepartment(Department department) {
        List<Employee> result = new ArrayList<>();
        if (department == null) {
            return result;
        }
        for (Employee emp : employees) {
            if (emp.getDepartment().getDepartmentId() == department.getDepartmentId()) {
                result.add(emp);
            }
        }

        return result;
    }

    public String updateEmployee(int id, String newPosition, Department newDepartment, EmployeeType newType, double a, double b) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                index = i;
            }
        }
        if (index == -1) {
            return "Employee not found.";
        }

        Employee old = employees.get(index);

        try {
            String position;
            Department dep;
            EmployeeType type;

            if (newPosition == null || newPosition.trim().isEmpty()) {
                position = old.getPosition();
            } else {
                position = newPosition;
            }

            if (newDepartment == null) {
                dep = old.getDepartment();
            } else {
                dep = newDepartment;
            }

            if (newType == null) {
                type = old.getType();
            } else {
                type = newType;
            }

            Employee updated;
            if (type == old.getType()) {
                old.setPosition(position);
                old.setDepartment(dep);
                updated = old;
            } else if (type == EmployeeType.FULL_TIME) {
                updated = new FullTimeEmployee(old.getId(), old.getName(), old.getAge(), dep, position, a, b);
            } else {
                updated = new PartTimeEmployee(old.getId(), old.getName(), old.getAge(), dep, position, a, b);
            }
            
            employees.set(index, updated);

        }
        catch(IllegalArgumentException e){
            return e.getMessage();
        }
        
        //saveEmployees();
        return null;
        

    }
    
    

    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

}
