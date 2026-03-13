public class Patient extends Person {
    private int age;
    private String condition;

    public Patient(String id, String name, String phone, int age, String condition) {
        super(id, name, phone);
        setAge(age);
        setCondition(condition);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("Condition cannot be empty.");
        }
        this.condition = condition;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Role: " + getRole() + ", Age: " + age + ", Condition: " + condition;
    }
}