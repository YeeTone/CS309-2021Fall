package assignment4.singleton.dao;

import assignment4.singleton.bean.Computer;

public interface ComputerDao {
    public int insertComputer(Computer computer);
    public int updateComputer(int id);
    public int deleteComputer(int id);
}
