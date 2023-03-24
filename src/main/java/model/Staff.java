package model;

import java.util.Date;

public class Staff implements Comparable<Staff>{
    private int staffId;
    private String fullName;
    private int gender;
    private String mail;
    private String phone;
    private Date hireDate;
    private int salary;
    private int departmentId;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //inner join
    private String nameDept;
    private int leadDept;

    public String getNameDept() {
        return nameDept;
    }

    public void setNameDept(String nameDept) {
        this.nameDept = nameDept;
    }

    public int getLeadDept() {
        return leadDept;
    }

    public void setLeadDept(int leadDept) {
        this.leadDept = leadDept;
    }

    public Staff(){

    }
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public int compareTo(Staff o) {
        if(this.getSalary() < o.getSalary()){
            return 1;
        }else if(this.getSalary() > o.getSalary())
            return -1;
        return 0;
    }
}
