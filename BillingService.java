public class BillingService extends HospitalService {
    private Patient patient;
    private double amount;

    public BillingService(String serviceName, Patient patient, double amount) {
        super(serviceName);
        this.patient = patient;
        setAmount(amount);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Billing amount cannot be negative.");
        }
        this.amount = amount;
    }

    @Override
    public void executeService() {
        System.out.println("Billing Service: " + getServiceName() +
                " | Patient: " + patient.getName() +
                " | Amount Due: KES " + amount);
    }
}