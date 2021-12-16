package assignment4.singleton.dao;

import assignment4.singleton.bean.Staff;

public interface StaffDao {
    public int insertStaff(Staff staff);
    public int deleteStaff(int id);
    public int updateStaff(int id);
}
