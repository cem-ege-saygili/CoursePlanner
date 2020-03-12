package domain;

public class Department {

    private String DepartmentName;

    public Department(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }
}
