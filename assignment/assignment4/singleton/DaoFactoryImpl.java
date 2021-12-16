package assignment4.singleton;



import assignment4.singleton.dao.ComputerDao;
import assignment4.singleton.dao.DaoFactory;
import assignment4.singleton.dao.StaffDao;

import java.lang.reflect.Constructor;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory {
    private static Properties propertiesSingleton = null;
    private static StaffDao staffDaoSingleton = null;
    private static ComputerDao computerDaoSingleton = null;

    public DaoFactoryImpl() {
    }

    @Override
    public StaffDao createStaffDao() {
        if (propertiesSingleton == null) {
            propertiesSingleton = PropertiesReader.readProperties("resources.properties");
        }

        if (staffDaoSingleton == null) {
            try {
                String className = propertiesSingleton.getProperty("StaffDao");
                Class<?> clazz = Class.forName(className);
                staffDaoSingleton = (StaffDao) instanceObject(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return staffDaoSingleton;
    }

    private <T> T instanceObject(Class<? extends T> clazz) {
        try {
            Constructor<? extends T> c = clazz.getDeclaredConstructor();
            return c.newInstance();
        } catch (Exception cnfe) {
            return null;
        }

    }

    @Override
    public ComputerDao createComputerDao() {
        if (propertiesSingleton == null) {
            propertiesSingleton = PropertiesReader.readProperties("resources.properties");
        }

        if (staffDaoSingleton == null) {
            try {
                String className = propertiesSingleton.getProperty("ComputerDao");
                Class<?> clazz = Class.forName(className);
                computerDaoSingleton = (ComputerDao) instanceObject(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return computerDaoSingleton;
    }
}