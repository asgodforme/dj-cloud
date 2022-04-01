package factoryModern.abstractFactory;

public class MiPhone implements Phone {

    public MiPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("生产小米手机");
    }
}
