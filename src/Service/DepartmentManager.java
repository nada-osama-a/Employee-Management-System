package Service;

import java.util.ArrayList;
import model.Department;

public class DepartmentManager {

    private ArrayList<Department> departments;

    public DepartmentManager() {
        departments = new ArrayList<>();
    }

    public boolean isAvailableId(int id) {
        for (Department d : departments) {
            if (id == d.getDepartmentId()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAvailableName(Department name) {
        for (Department d : departments) {
            if (d.hasSameName(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean addDepartment(Department dep) {
        if (dep == null) {
            return false;
        } else {
            if (isAvailableId(dep.getDepartmentId())) {
                if (isAvailableName(dep.getDepartmentName())) {
                    departments.add(dep);
                    return true;

                }
                return false;

            }
            return false;

        }
    }

    public ArrayList<Department> getAllDepartments() {

        return new ArrayList<>(departments);
    }

}
