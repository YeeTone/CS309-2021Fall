package assignment4.abstractFactory.dao;

public interface DaoFactory {
    ComputerDao createComputerDao();
    StaffDao createStaffDao();
}
