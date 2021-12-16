package assignment1.people;

import assignment1.record.HealthDeclarationForm;
import assignment1.system.HealthDeclarationSystem;

import java.lang.reflect.Field;
import java.util.List;

public class Administrator extends SUSTechMember{

    public List<HealthDeclarationForm> viewAllDeclarations(){
        return HealthDeclarationSystem.getSUSTechRecords();
    }

    public void addDeclarationForm(String s){
        HealthDeclarationForm.addRecordType(s);
    }

    public void deleteDeclarationForm(String s){
        HealthDeclarationForm.removeRecordTypes(s);
    }

    public void changeDeclarationForm(String before, String after){
        HealthDeclarationForm.changeRecordTypes(before, after);
    }

    public void registerAccount(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        SUSTechMember m = (SUSTechMember) Class.forName(type).newInstance();
        HealthDeclarationSystem.addMembers(m);
        for (Field f:m.getClass().getFields()){
            enterAccountInformation(m,f.getName(),null);
        }

    }

    private void enterAccountInformation(SUSTechMember m, String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        try{
            Field f = m.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(m,value);
        }catch (Exception e){
            return;
        }

    }

    public void modifyAccountInformation(SUSTechMember m,String type, Object changed){
        SUSTechMember sm = HealthDeclarationSystem.getMemberById(m.getID());
        if(sm == null){
            return;
        }

        Class<?> clazz = sm.getClass();
        try{
            Field f = clazz.getDeclaredField(type);
            f.set(m,changed);
        }catch (Exception e){
            return;
        }

    }
}
