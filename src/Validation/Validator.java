package Validation;

public class Validator {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        try {
            int number = Integer.parseInt(id.trim());
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidAge(String age) {
        if (age == null || age.trim().isEmpty()) {
            return false;
        }

        try {
            int number = Integer.parseInt(age.trim());
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidName(String name) {
        return isNotEmpty(name) && name.trim().matches("^[\\p{L} ]+$");
    }
    
    public static boolean isValidDepartment(String department) {
        return isNotEmpty(department);
    }

    public static boolean isValidPosition(String position) {
        return isNotEmpty(position);
    }

    public static boolean isValidBasicSalary(String salary) {
        return isNonNegativeNumber(salary);
    }

    public static boolean isValidBonus(String bonus) {
        return isNonNegativeNumber(bonus);
    }

    public static boolean isValidHourlyRate(String rate) {
        return isNonNegativeNumber(rate);
    }

    public static boolean isValidHoursWorked(String hours) {
        return isNonNegativeNumber(hours);
    }

    public static boolean isNonNegativeNumber(String value) {
        if (!isNotEmpty(value)) {
            return false;
        }

        try {
            double number = Double.parseDouble(value.trim());
            return number >= 0 && Double.isFinite(number);
        } catch (NumberFormatException e ) {
            return false;
        }
    }
}
