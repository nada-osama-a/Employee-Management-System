package Service;

import java.util.ArrayList;
import model.Employee;

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
