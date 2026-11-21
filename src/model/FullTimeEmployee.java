package model;

public class FullTimeEmployee extends Employee {
    private static final long serialVersionUID = 1L;

    private double basicSalary;
    private double bonus;

    public FullTimeEmployee(int id, String name, int age, Department department,
                            String position, double basicSalary, double bonus) {
        super(id, name, age, department, position);
        setBasicSalary(basicSalary);
        setBonus(bonus);
    }

    @Override
    public double calculateSalary() {
        return basicSalary + bonus;
    }

    @Override
    public EmployeeType getType() { return EmployeeType.FULL_TIME; }

    public double getBasicSalary() { return basicSalary; }
    public double getBonus() { return bonus; }

    public final void setBasicSalary(double basicSalary) {
        if (basicSalary < 0) throw new IllegalArgumentException("Basic salary cannot be negative.");
        this.basicSalary = basicSalary;
    }

    public final void setBonus(double bonus) {
        if (bonus < 0) throw new IllegalArgumentException("Bonus cannot be negative.");
        this.bonus = bonus;
    }
}