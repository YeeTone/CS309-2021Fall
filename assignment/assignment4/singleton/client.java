package assignment4.singleton;

import assignment4.singleton.bean.Computer;
import assignment4.singleton.bean.Staff;
import assignment4.singleton.dao.ComputerDao;
import assignment4.singleton.dao.DaoFactory;
import assignment4.singleton.dao.StaffDao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class client {
    public static void main(String [] args){
        DaoFactory df = new DaoFactoryImpl();
        StaffDao sd = df.createStaffDao();
        ComputerDao cd = df.createComputerDao();
        test(sd, cd);
    }

    public static void test(StaffDao staffDao, ComputerDao computerDao){
        Scanner input=new Scanner(System.in);
        int op=-1;
        do{
            try{
                op=input.nextInt();
                switch (op){
                    case 1:
                        staffDao.insertStaff(new Staff());
                        break;
                    case 2:
                        staffDao.updateStaff(1);
                        break;
                    case 3:
                        staffDao.deleteStaff(1);
                        break;
                    case 4:
                        computerDao.insertComputer(new Computer(1));
                        break;
                    case 5:
                        computerDao.updateComputer(1);
                        break;
                    case 6:
                        computerDao.deleteComputer(1);
                        break;

                }
            }catch(InputMismatchException e){
                System.out.println("Exception "+e);
            }
        }while(op!=0);
    }
}
