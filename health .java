interface Payable {
    double calculatePay();
    void pay();
}

interface Schedulable {
    void schedule();
    void reschedule(String newDate, String newTime);
}


abstract class Person {
    private String id;
    private String name;
    private String phone;

    public Person(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }


    public abstract String getRole();


    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Phone: " + phone;
    }
}

abstract class HospitalService {
    private String serviceName;

    public HospitalService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public abstract void executeService();
}


class Doctor extends Person implements Payable {
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

class Nurse extends Person implements Payable {
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

class Patient extends Person {
    private int age;
    private String condition;

    public Patient(String id, String name, String phone, int age, String condition) {
        super(id, name, phone);
        this.age = age;
        this.condition = condition;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Role: " + getRole() + ", Age: " + age + ", Condition: " + condition;
    }
}


class Appointment implements Schedulable {
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

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

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

class BillingService extends HospitalService {
    private Patient patient;
    private double amount;

    public BillingService(String serviceName, Patient patient, double amount) {
        super(serviceName);
        this.patient = patient;
        this.amount = amount;
    }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public void executeService() {
        System.out.println("Billing Service: " + getServiceName() +
                " | Patient: " + patient.getName() +
                " | Amount Due: KES " + amount);
    }
}


class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public void showDepartment() {
        System.out.println("Department: " + departmentName);
    }
}

