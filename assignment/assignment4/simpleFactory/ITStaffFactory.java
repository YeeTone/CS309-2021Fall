package assignment4.simpleFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ITStaffFactory {
    private static final Map<Integer, Constructor<?>> constructorMap = new HashMap<>();

    public static ITStaff createITStaff(int v){
        switch (v) {
            case 1 -> {
                return new ITManager();
            }
            case 2 -> {
                return new Developer();
            }

            case 3 ->{
                return new Tester();
            }

            default -> {
                return null;
            }
        }
    }

    /*static {
        try{
            constructorMap.put(1, ITManager.class.getDeclaredConstructor());
            constructorMap.put(2, Developer.class.getDeclaredConstructor());
            constructorMap.put(3, Tester.class.getDeclaredConstructor());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ITStaff createITStaff(int v){
        try{
            return (ITStaff) constructorMap.get(v).newInstance();
        }catch (Exception e){
            return null;
        }
    }*/
}
