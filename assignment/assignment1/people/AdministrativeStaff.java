package assignment1.people;

import assignment1.record.HealthDeclarationForm;
import assignment1.system.HealthDeclarationSystem;

import java.util.List;

public class AdministrativeStaff extends SUSTechMember{
    public void getNotSubmitted(){

    }

    public List<HealthDeclarationForm> viewDeclarationsInDepartment(){
        return HealthDeclarationSystem.getRecordsByDepartment(this.department);
    }
}
