package model;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int age;
    private Department department;
    private String position;

    public Employee(int id, String name, int age, Department department, String position) {
        setId(id);
        setName(name);
        setAge(age);
        setDepartment(department);
        setPosition(position);
    }

    public abstract double calculateSalary();

    public abstract EmployeeType getType();

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public Department getDepartment() { return department; }
    public String getPosition() { return position; }

    public final void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Employee ID must be a positive integer.");
        this.id = id;
    }

    public final void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }

    public final void setAge(int age) {
        if (age <= 0) throw new IllegalArgumentException("Age must be a positive integer.");
        this.age = age;
    }

    public final void setDepartment(Department department) {
        if (department == null)
            throw new IllegalArgumentException("Department cannot be empty.");
        this.department = department;
    }

    public final void setPosition(String position) {
        if (position == null || position.trim().isEmpty())
            throw new IllegalArgumentException("Position cannot be empty.");
        this.position = position.trim();
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Name=" + name + ", Age=" + age + ", Dept=" + department
                + ", Position=" + position + ", Type=" + getType()
                + ", Salary=" + calculateSalary();
    }
}