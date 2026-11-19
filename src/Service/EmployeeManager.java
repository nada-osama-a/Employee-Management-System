/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.ArrayList;
import model.Employee;
/**
 *
 * @author Yomna Mohamed
 */
public class EmployeeManager {
    private ArrayList<Employee>employees;

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

    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
    
    
}
