package assignment4.factoryMethod;

public class ITDeveloperFactory implements ITStaffFactoryInterface{
    @Override
    public ITStaff createITStaff() {
        return new Developer();
    }
}
