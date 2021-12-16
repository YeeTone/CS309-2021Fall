package assignment4.singleton.dao;

public class SqlServerDaoFactory implements DaoFactory{
    @Override
    public ComputerDao createComputerDao() {
        return new SqlServerComputerDao();
    }

    @Override
    public StaffDao createStaffDao() {
        return new SqlServerStaffDao();
    }
}
