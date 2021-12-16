package assignment4.singleton.dao;

public interface DaoFactory {
    ComputerDao createComputerDao();
    StaffDao createStaffDao();
}
