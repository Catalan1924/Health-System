public class Department {
    private String departmentName;

    public Department(String departmentName) {
        setDepartmentName(departmentName);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }
        this.departmentName = departmentName;
    }

    public void showDepartment() {
        System.out.println("Department: " + departmentName);
    }
}