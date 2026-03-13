public class HospitalManagementSystem {
    public static void main(String[] args) {

        Doctor doc1 = new Doctor("D001", "Sunday George", "0712345678", "Cardiology", 180000);
        Nurse nurse1 = new Nurse("N001", "Kendi Kamau", "0798765432", "Emergency", 500, 160);
        Patient patient1 = new Patient("P001", "Shem Oduor", "0700111222", 18, "Fever");

        Person p1 = doc1;
        Person p2 = nurse1;
        Person p3 = patient1;

        System.out.println("PERSON DETAILS");
        System.out.println(p1.getDetails());
        System.out.println(p2.getDetails());
        System.out.println(p3.getDetails());

        doc1.pay();
        nurse1.pay();

        nurse1.assistDoctor(doc1);
        doc1.diagnose(patient1);

        Appointment appt1 = new Appointment("A100", patient1, doc1, "2026-03-01", "10:30 AM");
        appt1.schedule();
        appt1.reschedule("2026-03-02", "2:00 PM");

        BillingService billing = new BillingService("Patient Billing", patient1, 3500);
        billing.executeService();

        Department dept = new Department("Cardiology");
        dept.showDepartment();
    }
}