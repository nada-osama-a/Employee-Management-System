
package files;

import java.io.*;
import java.util.ArrayList;
import model.Department;
import model.Employee;

public class FileHandler {
    private static final String DATA_FOLDER = "data";
    private static final String EMPLOYEES_FILE = DATA_FOLDER + File.separator + "employees.ser";
    private static final String DEPARTMENTS_FILE = DATA_FOLDER + File.separator + "departments.ser";

    public FileHandler() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public boolean saveEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(EMPLOYEES_FILE))) // make new one if
                                                                                                    // needed
        {
            out.writeObject(employees);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean saveDepartment(ArrayList<Department> departments) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DEPARTMENTS_FILE))) // make new one if
                                                                                                      // needed
        {
            out.writeObject(departments);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Employee> loadEmployees() {
        File file = new File(EMPLOYEES_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Department> loadDepartments() {
        File file = new File(DEPARTMENTS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Department>) in.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            return new ArrayList<>();
        }
    }

}
