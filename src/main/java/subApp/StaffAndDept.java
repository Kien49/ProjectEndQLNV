package subApp;

import dao.DeptDAO;
import dao.StaffDAO;
import model.Department;
import model.Staff;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StaffAndDept {
    private StaffDAO staffDAO = new StaffDAO();
    private DeptDAO deptDAO = new DeptDAO();
    private Util util = new Util();

    public void addDeleteStaffofDept(Scanner in){
        List<Department> deptList = deptDAO.getAll();

        System.out.print("\t\tNhập mã nhân viên: ");
        Staff sId = util.checkStaffId(in);
        if(sId == null){
            return;
        }
        if(sId.getDepartmentId()==0){
            System.out.println("\t\tNhân viên này đang không thuộc phòng ban nào");
            System.out.println("\t\tChọn phòng ban để thêm nhân viên " +sId.getFullName() +" vào");

            int numberDept = util.chooseDepartment(in);
            if(numberDept< 1 || numberDept> deptList.size()){
                System.out.println("Lựa chọn không hợp lệ!!!");
                return;
            }
            int idDept = deptList.get(numberDept-1).getDeptId();
            sId.setDepartmentId(idDept);
            staffDAO.updateIdDept(sId, sId.getStaffId());

            System.out.println("Nhân viên "+sId.getFullName() +" đã thêm vào phòng ban ");
            return;
        }
        List<Staff> staffList = staffDAO.innerJoinMemberDept(sId.getDepartmentId());

        System.out.println("\t\tNhân viên này đang thuộc phòng ban " + staffList.get(0).getNameDept());
        System.out.println("\t\tBạn có muốn xóa " + sId.getFullName()+" ra khỏi phòng ban " + staffList.get(0).getNameDept());
        System.out.println("\t\t\t1: Có");
        System.out.println("\t\t\t2: Không");
        System.out.print("\t\t\tNhập lựa chọn: ");
        int option;
        try {
            option = Integer.parseInt(in.nextLine());

        } catch (Exception e) {
            System.out.println("Nhập sai định dạng");
            return;
        }
        if(option!=1){
            return;
        }

        for (int i = 0; i < staffList.size(); i++) {
            if(sId.getStaffId() == staffList.get(i).getLeadDept()){
                //Department dept = deptDAO.getById(sId.getDepartmentId());
                deptDAO.deleteLead(sId.getDepartmentId());
            }
        }

        staffDAO.deleteDept(sId.getStaffId());

        System.out.println("Xóa phòng ban thành công");
    }

    public void swapDept(Scanner in){
        List<Department> deptList = deptDAO.getAll();

        System.out.print("\t\tNhập mã nhân viên: ");
        Staff sId = util.checkStaffId(in);
        if(sId == null){
            return;
        }
        if(sId.getDepartmentId()==0){
            System.out.println("\t\tNhân viên này đang không thuộc phòng ban nào");
            return;
        }
        List<Staff> staffList = staffDAO.innerJoinMemberDept(sId.getDepartmentId());

        System.out.println("\t\tNhân viên này đang thuộc phòng ban " + staffList.get(0).getNameDept());
        System.out.println("\t\tChọn phòng ban để chuyển nhân viên " +sId.getFullName() +" vào");

        System.out.println("\t\t\t0. Thoát");
        int numberDept = util.chooseDepartment(in);
        if(numberDept< 0 || numberDept> deptList.size()){
            System.out.println("Lựa chọn không hợp lệ!!!");
            return;
        }
        if(numberDept ==0 ){
            return;
        }
        int idDept = deptList.get(numberDept-1).getDeptId();
        for (int i = 0; i < staffList.size(); i++) {
            if(sId.getStaffId() == staffList.get(i).getLeadDept()){
                //Department dept = deptDAO.getById(sId.getDepartmentId());
                deptDAO.deleteLead(sId.getDepartmentId());
            }
        }
        sId.setDepartmentId(idDept);
        staffDAO.updateIdDept(sId, sId.getStaffId());

        System.out.println("Nhân viên "+sId.getFullName() +" đã thêm vào phòng ban mới ");
    }
}
