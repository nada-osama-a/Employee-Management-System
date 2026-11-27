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
        return save(EMPLOYEES_FILE, employees);
    }

    public boolean saveDepartment(ArrayList<Department> departments) {
        return save(DEPARTMENTS_FILE, departments);
    }

    public ArrayList<Employee> loadEmployees() {
        return load(EMPLOYEES_FILE);
    }

    public ArrayList<Department> loadDepartments() {
        return load(DEPARTMENTS_FILE);
    }

    private boolean save(String path, Object data) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(data);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> ArrayList<T> load(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            return (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            return new ArrayList<>();
        }
    }
}
