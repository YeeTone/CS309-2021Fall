package assignment4.abstractFactory.dao;

import assignment4.abstractFactory.bean.Computer;

public interface ComputerDao {
    public int insertComputer(Computer computer);
    public int updateComputer(int id);
    public int deleteComputer(int id);
}
