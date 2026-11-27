package Service;

import java.util.ArrayList;
import java.util.List;
import model.*;
import files.FileHandler;

public class EmployeeManager {

    private FileHandler fileHandler;
    private ArrayList<Employee> employees;

    public EmployeeManager() {
        fileHandler = new FileHandler();
        employees = fileHandler.loadEmployees();
    }

    public boolean saveToFile() {
        return fileHandler.saveEmployees(employees);
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
        if (emp == null || !isAvailableId(emp.getId())) {
            return false;
        }

        employees.add(emp);

        if (!saveToFile()) {
            employees.remove(emp);
            return false;
        }

        return true;
    }

    public Employee searchById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }

        return null;
    }

    public List<Employee> searchByName(String name) {
        List<Employee> result = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            return result;
        }

        String query = name.trim().toLowerCase();

        for (Employee emp : employees) {
            if (emp.getName().toLowerCase().contains(query)) {
                result.add(emp);
            }
        }

        return result;
    }

    public List<Employee> filterByDepartment(Department department) {
        List<Employee> result = new ArrayList<>();

        if (department == null) {
            return result;
        }

        for (Employee emp : employees) {
            if (emp.getDepartment().equalsIgnoreCase(
                    department.getDepartmentName())) {
                result.add(emp);
            }
        }

        return result;
    }

    public String updateEmployee(int id, String newPosition,
            Department newDepartment, EmployeeType newType,
            double a, double b) {

        int index = -1;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return "Employee not found.";
        }

        Employee old = employees.get(index);

        try {
            String position;
            String dep;
            EmployeeType type;

            if (newPosition == null || newPosition.trim().isEmpty()) {
                position = old.getPosition();
            } else {
                position = newPosition;
            }

            if (newDepartment == null) {
                dep = old.getDepartment();
            } else {
                dep = newDepartment.getDepartmentName();
            }

            if (newType == null) {
                type = old.getType();
            } else {
                type = newType;
            }

            Employee updated;

            if (type == EmployeeType.FULL_TIME) {

                updated = new FullTimeEmployee(
                        old.getId(),
                        old.getName(),
                        old.getAge(),
                        dep,
                        position,
                        a,
                        b
                );

            } else {

                updated = new PartTimeEmployee(
                        old.getId(),
                        old.getName(),
                        old.getAge(),
                        dep,
                        position,
                        a,
                        b
                );
            }

            employees.set(index, updated);

            if (!saveToFile()) {
                employees.set(index, old);
                return "Error saving data to file.";
            }

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        return null;
    }

    public boolean deleteEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getId() == id) {

                Employee removed = employees.remove(i);

                if (!saveToFile()) {
                    employees.add(i, removed);
                    return false;
                }

                return true;
            }
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
}
