public class Doctor extends Person implements Payable {
    private String specialization;
    private double monthlySalary;

    public Doctor(String id, String name, String phone, String specialization, double monthlySalary) {
        super(id, name, phone);
        this.specialization = specialization;
        this.monthlySalary = monthlySalary;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Role: " + getRole() + ", Specialization: " + specialization;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }

    @Override
    public void pay() {
        System.out.println(getName() + " has been paid KES " + calculatePay());
    }

    public void diagnose(Patient patient) {
        System.out.println("Dr. " + getName() + " is diagnosing patient " + patient.getName());
    }
}