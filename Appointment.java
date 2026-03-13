public class Appointment implements Schedulable {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date, String time) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    @Override
    public void schedule() {
        System.out.println("Appointment Scheduled: " + appointmentId +
                " | Patient: " + patient.getName() +
                " | Doctor: " + doctor.getName() +
                " | Date: " + date + " | Time: " + time);
    }

    @Override
    public void reschedule(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
        System.out.println("Appointment " + appointmentId + " rescheduled to " + date + " at " + time);
    }
}