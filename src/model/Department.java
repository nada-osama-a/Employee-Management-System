package model;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private int departmentId;
    private String departmentName;

    public Department(int departmentId, Department departmentName) {
        setDepartmentId(departmentId);
        setDepartmentName(departmentName);
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public final void setDepartmentId(int departmentId) {
        if (departmentId <= 0) {
            throw new IllegalArgumentException("Department ID must be a positive integer.");
        }
        this.departmentId = departmentId;
    }

    public final void setDepartmentName(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
        this.departmentName = departmentName.trim();
    }

    public boolean hasSameName(String other) {
        return other != null && departmentName.equalsIgnoreCase(other.trim());
    }

    @Override
    public String toString() {
        return departmentName;
    }
}
