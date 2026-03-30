import java.io.*;
import java.util.*;

/**
 * DataManager handles persistence and retrieval of hospital data
 * Uses Collections (ArrayList, HashMap) to store objects and file I/O for persistence
 */
public class DataManager {
    private static final String DATA_DIRECTORY = "hospital_data/";
    private static final String PATIENTS_FILE = DATA_DIRECTORY + "patients.dat";
    private static final String DOCTORS_FILE = DATA_DIRECTORY + "doctors.dat";
    private static final String NURSES_FILE = DATA_DIRECTORY + "nurses.dat";
    private static final String APPOINTMENTS_FILE = DATA_DIRECTORY + "appointments.dat";

    // Collections to store objects
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private List<Appointment> appointments;
    private Map<String, List<Appointment>> appointmentsByDoctor;

    public DataManager() {
        // Initialize collections
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.appointmentsByDoctor = new HashMap<>();

        // Create data directory if it doesn't exist
        new File(DATA_DIRECTORY).mkdirs();

        // Load existing data
        loadAllData();
    }

    // ==================== PATIENT MANAGEMENT ====================
    public void addPatient(Patient patient) {
        if (patients.stream().noneMatch(p -> p.getId().equals(patient.getId()))) {
            patients.add(patient);
            System.out.println("Patient " + patient.getName() + " added successfully.");
        } else {
            System.out.println("Patient with ID " + patient.getId() + " already exists.");
        }
    }

    public Patient getPatient(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public void removePatient(String id) {
        patients.removeIf(p -> p.getId().equals(id));
        System.out.println("Patient with ID " + id + " removed.");
    }

    // ==================== DOCTOR MANAGEMENT ====================
    public void addDoctor(Doctor doctor) {
        if (doctors.stream().noneMatch(d -> d.getId().equals(doctor.getId()))) {
            doctors.add(doctor);
            appointmentsByDoctor.put(doctor.getId(), new ArrayList<>());
            System.out.println("Doctor " + doctor.getName() + " added successfully.");
        } else {
            System.out.println("Doctor with ID " + doctor.getId() + " already exists.");
        }
    }

    public Doctor getDoctor(String id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public void removeDoctor(String id) {
        doctors.removeIf(d -> d.getId().equals(id));
        appointmentsByDoctor.remove(id);
        System.out.println("Doctor with ID " + id + " removed.");
    }

    // ==================== NURSE MANAGEMENT ====================
    public void addNurse(Nurse nurse) {
        if (nurses.stream().noneMatch(n -> n.getId().equals(nurse.getId()))) {
            nurses.add(nurse);
            System.out.println("Nurse " + nurse.getName() + " added successfully.");
        } else {
            System.out.println("Nurse with ID " + nurse.getId() + " already exists.");
        }
    }

    public Nurse getNurse(String id) {
        return nurses.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Nurse> getAllNurses() {
        return new ArrayList<>(nurses);
    }

    public void removeNurse(String id) {
        nurses.removeIf(n -> n.getId().equals(id));
        System.out.println("Nurse with ID " + id + " removed.");
    }

    // ==================== APPOINTMENT MANAGEMENT ====================
    public void addAppointment(Appointment appointment) {
        if (appointments.stream().noneMatch(a -> a.getAppointmentId().equals(appointment.getAppointmentId()))) {
            appointments.add(appointment);
            
            // Store by doctor for quick lookup
            String doctorId = appointment.getDoctor().getId();
            appointmentsByDoctor.computeIfAbsent(doctorId, k -> new ArrayList<>()).add(appointment);
            
            System.out.println("Appointment " + appointment.getAppointmentId() + " scheduled successfully.");
        } else {
            System.out.println("Appointment with ID " + appointment.getAppointmentId() + " already exists.");
        }
    }

    public Appointment getAppointment(String id) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return new ArrayList<>(appointmentsByDoctor.getOrDefault(doctorId, new ArrayList<>()));
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatient().getId().equals(patientId)) {
                result.add(a);
            }
        }
        return result;
    }

    // ==================== DISPLAY SUMMARIES ====================
    public void displayAllData() {
        System.out.println("\n===== HOSPITAL DATA SUMMARY =====");
        System.out.println("\n--- PATIENTS (" + patients.size() + ") ---");
        for (Patient p : patients) {
            System.out.println("  " + p.getDetails());
        }

        System.out.println("\n--- DOCTORS (" + doctors.size() + ") ---");
        for (Doctor d : doctors) {
            System.out.println("  " + d.getDetails());
        }

        System.out.println("\n--- NURSES (" + nurses.size() + ") ---");
        for (Nurse n : nurses) {
            System.out.println("  " + n.getDetails());
        }

        System.out.println("\n--- APPOINTMENTS (" + appointments.size() + ") ---");
        for (Appointment a : appointments) {
            System.out.println("  ID: " + a.getAppointmentId() + " | Patient: " + a.getPatient().getName() +
                    " | Doctor: " + a.getDoctor().getName() + " | Date: " + a.getDate() + " | Time: " + a.getTime());
        }
    }

    // ==================== FILE PERSISTENCE ====================

    /**
     * Save all data to files
     */
    public void saveAllData() {
        savePatients();
        saveDoctors();
        saveNurses();
        saveAppointments();
        System.out.println("\n✓ All data saved successfully!");
    }

    private void savePatients() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATIENTS_FILE))) {
            oos.writeObject(patients);
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
        }
    }

    private void saveDoctors() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOCTORS_FILE))) {
            oos.writeObject(doctors);
        } catch (IOException e) {
            System.err.println("Error saving doctors: " + e.getMessage());
        }
    }

    private void saveNurses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NURSES_FILE))) {
            oos.writeObject(nurses);
        } catch (IOException e) {
            System.err.println("Error saving nurses: " + e.getMessage());
        }
    }

    private void saveAppointments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPOINTMENTS_FILE))) {
            oos.writeObject(appointments);
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
        }
    }

    /**
     * Load all data from files
     */
    public void loadAllData() {
        loadPatients();
        loadDoctors();
        loadNurses();
        loadAppointments();
        
        // Rebuild the appointmentsByDoctor map
        appointmentsByDoctor.clear();
        for (Appointment a : appointments) {
            String doctorId = a.getDoctor().getId();
            appointmentsByDoctor.computeIfAbsent(doctorId, k -> new ArrayList<>()).add(a);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPatients() {
        File file = new File(PATIENTS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                patients = (List<Patient>) ois.readObject();
                System.out.println("Loaded " + patients.size() + " patients from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading patients: " + e.getMessage());
                patients = new ArrayList<>();
            }
        } else {
            patients = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadDoctors() {
        File file = new File(DOCTORS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                doctors = (List<Doctor>) ois.readObject();
                System.out.println("Loaded " + doctors.size() + " doctors from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading doctors: " + e.getMessage());
                doctors = new ArrayList<>();
            }
        } else {
            doctors = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadNurses() {
        File file = new File(NURSES_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                nurses = (List<Nurse>) ois.readObject();
                System.out.println("Loaded " + nurses.size() + " nurses from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading nurses: " + e.getMessage());
                nurses = new ArrayList<>();
            }
        } else {
            nurses = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAppointments() {
        File file = new File(APPOINTMENTS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                appointments = (List<Appointment>) ois.readObject();
                System.out.println("Loaded " + appointments.size() + " appointments from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading appointments: " + e.getMessage());
                appointments = new ArrayList<>();
            }
        } else {
            appointments = new ArrayList<>();
        }
    }

    /**
     * Clear all data (for testing purposes)
     */
    public void clearAllData() {
        patients.clear();
        doctors.clear();
        nurses.clear();
        appointments.clear();
        appointmentsByDoctor.clear();
        System.out.println("All data has been cleared from memory.");
    }

    /**
     * Export data to CSV format for easy viewing
     */
    public void exportToCSV() {
        try {
            exportPatientsToCSV();
            exportDoctorsToCSV();
            exportNursesToCSV();
            exportAppointmentsToCSV();
            System.out.println("✓ Data exported to CSV successfully!");
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
        }
    }

    private void exportPatientsToCSV() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIRECTORY + "patients.csv"))) {
            writer.println("ID,Name,Phone,Age,Condition");
            for (Patient p : patients) {
                writer.printf("%s,%s,%s,%d,%s%n", p.getId(), p.getName(), p.getPhone(), p.getAge(), p.getCondition());
            }
        }
    }

    private void exportDoctorsToCSV() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIRECTORY + "doctors.csv"))) {
            writer.println("ID,Name,Phone,Specialization,MonthlySalary");
            for (Doctor d : doctors) {
                writer.printf("%s,%s,%s,%s,%.2f%n", d.getId(), d.getName(), d.getPhone(), d.getSpecialization(), d.getMonthlySalary());
            }
        }
    }

    private void exportNursesToCSV() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIRECTORY + "nurses.csv"))) {
            writer.println("ID,Name,Phone,Department,HourlyRate,HoursWorked");
            for (Nurse n : nurses) {
                writer.printf("%s,%s,%s,%s,%.2f,%d%n", n.getId(), n.getName(), n.getPhone(), n.getDepartment(), n.getHourlyRate(), n.getHoursWorked());
            }
        }
    }

    private void exportAppointmentsToCSV() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIRECTORY + "appointments.csv"))) {
            writer.println("AppointmentID,PatientName,DoctorName,Date,Time");
            for (Appointment a : appointments) {
                writer.printf("%s,%s,%s,%s,%s%n", a.getAppointmentId(), a.getPatient().getName(), a.getDoctor().getName(), a.getDate(), a.getTime());
            }
        }
    }
}
