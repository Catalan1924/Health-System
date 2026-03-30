import java.io.Serializable;

public class Nurse extends Person implements Payable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String department;
    private double hourlyRate;
    private int hoursWorked;

    public Nurse(String id, String name, String phone, String department, double hourlyRate, int hoursWorked) {
        super(id, name, phone);
        setDepartment(department);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        this.department = department;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Role: " + getRole() + ", Department: " + department;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public void pay() {
        System.out.println(getName() + " has been paid KES " + calculatePay());
    }

    public void assistDoctor(Doctor doctor) {
        System.out.println(getName() + " is assisting Dr. " + doctor.getName());
    }
}