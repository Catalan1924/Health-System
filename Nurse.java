public class Nurse extends Person implements Payable {
    private String department;
    private double hourlyRate;
    private int hoursWorked;

    public Nurse(String id, String name, String phone, String department, double hourlyRate, int hoursWorked) {
        super(id, name, phone);
        this.department = department;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

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