import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalManagementSystem {

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty.");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();

                if (value < 0) {
                    throw new IllegalArgumentException("Value cannot be negative.");
                }

                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();

                if (value < 0) {
                    throw new IllegalArgumentException("Value cannot be negative.");
                }

                return value;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("===== HOSPITAL MANAGEMENT SYSTEM =====");

            System.out.println("\nEnter Doctor Details");
            String docId = readNonEmptyString(scanner, "Doctor ID: ");
            String docName = readNonEmptyString(scanner, "Doctor Name: ");
            String docPhone = readNonEmptyString(scanner, "Doctor Phone: ");
            String docSpecialization = readNonEmptyString(scanner, "Doctor Specialization: ");
            double docSalary = readPositiveDouble(scanner, "Doctor Monthly Salary: ");

            Doctor doctor = new Doctor(docId, docName, docPhone, docSpecialization, docSalary);

            System.out.println("\nEnter Nurse Details");
            String nurseId = readNonEmptyString(scanner, "Nurse ID: ");
            String nurseName = readNonEmptyString(scanner, "Nurse Name: ");
            String nursePhone = readNonEmptyString(scanner, "Nurse Phone: ");
            String nurseDepartment = readNonEmptyString(scanner, "Nurse Department: ");
            double nurseHourlyRate = readPositiveDouble(scanner, "Nurse Hourly Rate: ");
            int nurseHoursWorked = readPositiveInt(scanner, "Nurse Hours Worked: ");

            Nurse nurse = new Nurse(nurseId, nurseName, nursePhone, nurseDepartment, nurseHourlyRate, nurseHoursWorked);

            System.out.println("\nEnter Patient Details");
            String patientId = readNonEmptyString(scanner, "Patient ID: ");
            String patientName = readNonEmptyString(scanner, "Patient Name: ");
            String patientPhone = readNonEmptyString(scanner, "Patient Phone: ");
            int patientAge = readPositiveInt(scanner, "Patient Age: ");
            String patientCondition = readNonEmptyString(scanner, "Patient Condition: ");

            Patient patient = new Patient(patientId, patientName, patientPhone, patientAge, patientCondition);

            System.out.println("\nEnter Appointment Details");
            String appointmentId = readNonEmptyString(scanner, "Appointment ID: ");
            String appointmentDate = readNonEmptyString(scanner, "Appointment Date: ");
            String appointmentTime = readNonEmptyString(scanner, "Appointment Time: ");

            Appointment appointment = new Appointment(appointmentId, patient, doctor, appointmentDate, appointmentTime);

            System.out.println("\nEnter Billing Details");
            String billingServiceName = readNonEmptyString(scanner, "Billing Service Name: ");
            double billingAmount = readPositiveDouble(scanner, "Billing Amount: ");

            BillingService billingService = new BillingService(billingServiceName, patient, billingAmount);

            System.out.println("\nEnter Department Details");
            String departmentName = readNonEmptyString(scanner, "Department Name: ");
            Department department = new Department(departmentName);

            System.out.println("\n===== SYSTEM OUTPUT =====");
            Person p1 = doctor;
            Person p2 = nurse;
            Person p3 = patient;

            System.out.println("\nPERSON DETAILS");
            System.out.println(p1.getDetails());
            System.out.println(p2.getDetails());
            System.out.println(p3.getDetails());

            System.out.println("\nSTAFF PAYROLL");
            doctor.pay();
            nurse.pay();

            System.out.println("\nMEDICAL WORKFLOW");
            nurse.assistDoctor(doctor);
            doctor.diagnose(patient);

            System.out.println("\nAPPOINTMENT");
            appointment.schedule();

            System.out.println("\nRESCHEDULING APPOINTMENT");
            String newDate = readNonEmptyString(scanner, "Enter new appointment date: ");
            String newTime = readNonEmptyString(scanner, "Enter new appointment time: ");
            appointment.reschedule(newDate, newTime);

            System.out.println("\nBILLING SERVICE");
            billingService.executeService();

            System.out.println("\nDEPARTMENT");
            department.showDepartment();

        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        } finally {
            System.out.println("\nProgram finished safely. Scanner closed.");
            scanner.close();
        }
    }
}