package model;

public class Department {
    private int deptId;
    private String deptName;
    private int deptHeadId;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    //innerjoin
    private  String nameLead;
    private int salaryLead;

    public Department(){

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

    public String getNameLead() {
        return nameLead;
    }

    public void setNameLead(String nameLead) {
        this.nameLead = nameLead;
    }

    public int getSalaryLead() {
        return salaryLead;
    }

    public void setSalaryLead(int salaryLead) {
        this.salaryLead = salaryLead;
    }
}
