package factoryModern.abstractFactory;

public class IPhone implements Phone {


    public IPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("生产苹果手机");
    }
}
