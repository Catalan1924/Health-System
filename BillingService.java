public class BillingService extends HospitalService {
    private Patient patient;
    private double amount;

    public BillingService(String serviceName, Patient patient, double amount) {
        super(serviceName);
        this.patient = patient;
        this.amount = amount;
    }

    @Override
    public void executeService() {
        System.out.println("Billing Service: " + getServiceName() +
                " | Patient: " + patient.getName() +
                " | Amount Due: KES " + amount);
    }
}