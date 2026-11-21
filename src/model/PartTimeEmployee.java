package model;

public class PartTimeEmployee extends Employee {
    private static final long serialVersionUID = 1L;

    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(int id, String name, int age, Department department,
            String position, double hourlyRate, double hoursWorked) {
        super(id, name, age, department, position);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public EmployeeType getType() {
        return EmployeeType.PART_TIME;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public final void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0)
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        this.hourlyRate = hourlyRate;
    }

    public final void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0)
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        this.hoursWorked = hoursWorked;
    }
}