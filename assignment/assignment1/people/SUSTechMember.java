package assignment1.people;

import assignment1.time.SUSTechTime;
import assignment1.record.HealthDeclarationForm;
import assignment1.system.HealthDeclarationSystem;

public abstract class SUSTechMember {
    protected String name;
    protected int age;
    protected String cellphoneNumber;
    protected String department;
    protected String ID;

    public void clickDeclareButton() throws CloneNotSupportedException {
        declareHealth();
    }

    public void declareHealth() throws CloneNotSupportedException {
        HealthDeclarationForm problem = getProblemFromSystem();
        HealthDeclarationForm record = new HealthDeclarationForm(this, (SUSTechTime) HealthDeclarationSystem.getSUSTechTime().clone());
        HealthDeclarationSystem.addRecord(record);
    }
    private HealthDeclarationForm getProblemFromSystem() throws CloneNotSupportedException {
        return HealthDeclarationSystem.getStandardForm(this);
    }

    public HealthDeclarationForm checkPrevious(){
        return HealthDeclarationSystem.checkPrevious(this);
    }

    public boolean hasDeclared(SUSTechTime t){
        return HealthDeclarationSystem.hasChecked(this,t);
    }

    public void getNotSubmittedHint(){

    }

    public String getID() {
        return ID;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cellphoneNumber='" + cellphoneNumber + '\'' +
                ", department='" + department + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
