package assignment4.factoryMethod;

public class ITTesterFactory implements ITStaffFactoryInterface{
    @Override
    public ITStaff createITStaff() {
        return new Tester();
    }
}
