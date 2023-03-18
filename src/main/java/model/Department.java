package model;

public class Department {
    private int deptId;
    private String deptName;
    private int deptHeadId;

    public Department(){

    }
    public Department(int deptId, String deptName, int deptHeadId) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptHeadId = deptHeadId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(int deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptHeadId=" + deptHeadId +
                '}';
    }
}
