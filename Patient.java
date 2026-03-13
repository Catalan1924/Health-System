public class Patient extends Person {
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