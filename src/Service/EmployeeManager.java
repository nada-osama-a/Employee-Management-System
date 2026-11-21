/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.util.ArrayList;
import java.util.List;
import model.Employee;
import model.Department;
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
    
    public Employee searchById(int id){
        for(Employee emp : employees){
            if(emp.getId() == id)
                return emp;
        }
        return null; 
    }
    
    public List<Employee> filterByDepartment(Department department){
        List<Employee> result = new ArrayList<>();
        if(department == null )
            return result;
        for(Employee emp : employees){
            if(emp.getDepartment() == department)
                result.add(emp);
        }
        
        return result;
    }
    
   
    
    
    

    public ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
    
    
}
