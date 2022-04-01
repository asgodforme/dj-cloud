package factoryModern;

import factoryModern.factory.AbstractFactory;
import factoryModern.factory.AppleFactory;
import factoryModern.simple.ComputerFactory;

public class CreateComputer {
    public static void main(String[] args) {
        ComputerFactory.createComputer("asus").make();

        AbstractFactory appleFactory = new AppleFactory();
        appleFactory.makePhone();
    }
}
