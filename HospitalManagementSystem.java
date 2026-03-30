import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

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
        DataManager dataManager = new DataManager();
        
        try {
            System.out.println("===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("Data loaded from persistent storage.\n");

            boolean running = true;
            while (running) {
                displayMainMenu();
                int choice = readMenuChoice(scanner, 1, 8);

                switch (choice) {
                    case 1:
                        addPatient(scanner, dataManager);
                        break;
                    case 2:
                        addDoctor(scanner, dataManager);
                        break;
                    case 3:
                        addNurse(scanner, dataManager);
                        break;
                    case 4:
                        addAppointment(scanner, dataManager);
                        break;
                    case 5:
                        dataManager.displayAllData();
                        break;
                    case 6:
                        dataManager.saveAllData();
                        break;
                    case 7:
                        dataManager.exportToCSV();
                        break;
                    case 8:
                        running = false;
                        System.out.println("\nSaving data before exit...");
                        dataManager.saveAllData();
                        System.out.println("Thank you for using Hospital Management System!");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        } finally {
            System.out.println("\nProgram finished safely. Scanner closed.");
            scanner.close();
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add Patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. Add Nurse");
        System.out.println("4. Add Appointment");
        System.out.println("5. View All Data");
        System.out.println("6. Save Data to Files");
        System.out.println("7. Export to CSV");
        System.out.println("8. Exit");
        System.out.print("Enter your choice (1-8): ");
    }

    private static int readMenuChoice(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < min || choice > max) {
                    System.out.print("Invalid choice! Please enter a number between " + min + " and " + max + ": ");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.nextLine();
            }
        }
    }

    private static void addPatient(Scanner scanner, DataManager dataManager) {
        try {
            System.out.println("\n===== ADD PATIENT =====");
            String patientId = readNonEmptyString(scanner, "Patient ID: ");
            String patientName = readNonEmptyString(scanner, "Patient Name: ");
            String patientPhone = readNonEmptyString(scanner, "Patient Phone: ");
            int patientAge = readPositiveInt(scanner, "Patient Age: ");
            String patientCondition = readNonEmptyString(scanner, "Patient Condition: ");

            Patient patient = new Patient(patientId, patientName, patientPhone, patientAge, patientCondition);
            dataManager.addPatient(patient);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    private static void addDoctor(Scanner scanner, DataManager dataManager) {
        try {
            System.out.println("\n===== ADD DOCTOR =====");
            String docId = readNonEmptyString(scanner, "Doctor ID: ");
            String docName = readNonEmptyString(scanner, "Doctor Name: ");
            String docPhone = readNonEmptyString(scanner, "Doctor Phone: ");
            String docSpecialization = readNonEmptyString(scanner, "Doctor Specialization: ");
            double docSalary = readPositiveDouble(scanner, "Doctor Monthly Salary: ");

            Doctor doctor = new Doctor(docId, docName, docPhone, docSpecialization, docSalary);
            dataManager.addDoctor(doctor);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    private static void addNurse(Scanner scanner, DataManager dataManager) {
        try {
            System.out.println("\n===== ADD NURSE =====");
            String nurseId = readNonEmptyString(scanner, "Nurse ID: ");
            String nurseName = readNonEmptyString(scanner, "Nurse Name: ");
            String nursePhone = readNonEmptyString(scanner, "Nurse Phone: ");
            String nurseDepartment = readNonEmptyString(scanner, "Nurse Department: ");
            double nurseHourlyRate = readPositiveDouble(scanner, "Nurse Hourly Rate: ");
            int nurseHoursWorked = readPositiveInt(scanner, "Nurse Hours Worked: ");

            Nurse nurse = new Nurse(nurseId, nurseName, nursePhone, nurseDepartment, nurseHourlyRate, nurseHoursWorked);
            dataManager.addNurse(nurse);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding nurse: " + e.getMessage());
        }
    }

    private static void addAppointment(Scanner scanner, DataManager dataManager) {
        try {
            System.out.println("\n===== ADD APPOINTMENT =====");
            
            // Get list of patients
            java.util.List<Patient> patients = dataManager.getAllPatients();
            if (patients.isEmpty()) {
                System.out.println("No patients available. Please add a patient first.");
                return;
            }
            
            System.out.println("Available Patients:");
            for (int i = 0; i < patients.size(); i++) {
                System.out.println((i + 1) + ". " + patients.get(i).getName() + " (ID: " + patients.get(i).getId() + ")");
            }
            int patientChoice = readMenuChoice(scanner, 1, patients.size()) - 1;
            Patient selectedPatient = patients.get(patientChoice);

            // Get list of doctors
            java.util.List<Doctor> doctors = dataManager.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println("No doctors available. Please add a doctor first.");
                return;
            }
            
            System.out.println("Available Doctors:");
            for (int i = 0; i < doctors.size(); i++) {
                System.out.println((i + 1) + ". " + doctors.get(i).getName() + " (ID: " + doctors.get(i).getId() + ")");
            }
            int doctorChoice = readMenuChoice(scanner, 1, doctors.size()) - 1;
            Doctor selectedDoctor = doctors.get(doctorChoice);

            String appointmentId = readNonEmptyString(scanner, "Appointment ID: ");
            String appointmentDate = readNonEmptyString(scanner, "Appointment Date (YYYY-MM-DD): ");
            String appointmentTime = readNonEmptyString(scanner, "Appointment Time (HH:MM): ");

            Appointment appointment = new Appointment(appointmentId, selectedPatient, selectedDoctor, appointmentDate, appointmentTime);
            dataManager.addAppointment(appointment);
            appointment.schedule();
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }
}