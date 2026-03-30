import java.io.Serializable;

public class Appointment implements Schedulable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date, String time) {
        if (appointmentId == null || appointmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID cannot be empty.");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty.");
        }
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Time cannot be empty.");
        }

        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public void schedule() {
        System.out.println("Appointment Scheduled: " + appointmentId +
                " | Patient: " + patient.getName() +
                " | Doctor: " + doctor.getName() +
                " | Date: " + date +
                " | Time: " + time);
    }

    @Override
    public void reschedule(String newDate, String newTime) {
        if (newDate == null || newDate.trim().isEmpty()) {
            throw new IllegalArgumentException("New date cannot be empty.");
        }
        if (newTime == null || newTime.trim().isEmpty()) {
            throw new IllegalArgumentException("New time cannot be empty.");
        }

        this.date = newDate;
        this.time = newTime;
        System.out.println("Appointment " + appointmentId + " rescheduled to " + date + " at " + time);
    }
}