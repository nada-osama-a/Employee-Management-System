package model;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private int departmentId;
    private Department departmentName;

    public Department(int departmentId, Department departmentName) {
        setDepartmentId(departmentId);
        setDepartmentName(departmentName);
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public Department getDepartmentName() {
        return departmentName;
    }

    public final void setDepartmentId(int departmentId) {
        if (departmentId <= 0) {
            throw new IllegalArgumentException("Department ID must be a positive integer.");
        }
        this.departmentId = departmentId;
    }

    public final void setDepartmentName(Department departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
        this.departmentName = departmentName;
    }

    public boolean hasSameName(Department other) {
        return other != null && departmentName == other;
    }

    //@Override
    //public String toString() { return departmentName; }
}
