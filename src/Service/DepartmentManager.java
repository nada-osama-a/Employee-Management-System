package Service;

import files.FileHandler;
import java.util.ArrayList;
import model.Department;

public class DepartmentManager {

    private final FileHandler fileHandler;
    private final ArrayList<Department> departments;

    public DepartmentManager() {
        fileHandler = new FileHandler();
        departments = fileHandler.loadDepartments();   
    }

    public boolean saveToFile() {
        return fileHandler.saveDepartment(departments);
    }

    public boolean isAvailableId(int id) {
        for (Department d : departments) {
            if (d.getDepartmentId() == id) {
                return false;
            }
        }
        return true;
    }

    public boolean isAvailableName(String name) {
        for (Department d : departments) {
            if (d.hasSameName(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean addDepartment(Department dep) {
        if (dep == null
                || !isAvailableId(dep.getDepartmentId())
                || !isAvailableName(dep.getDepartmentName())) {
            return false;
        }
        departments.add(dep);
        if (!saveToFile()) {
            departments.remove(dep);   
            return false;
        }
        return true;
    }

    public ArrayList<Department> getAllDepartments() {
        return new ArrayList<>(departments);
    }
}
