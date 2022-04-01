package factoryModern.simple;

/*
 * 优点： 使用户根据参数获得对应的类实例，避免了直接实例化类，降低了耦合性
 * 缺点：
 *  1.可实例化的类型再编译期间已经被确定，如果增加新类型，则需要修改工厂。
 * 2. 违背了开放原则
 * 3.简单工厂需要知道所有要生成的类型，当子类过多时或者子类层次过多时不适合使用。
 */
public class ComputerFactory {
    public static Computer createComputer(String type) {
        Computer mComputer = null;
        switch (type) {
            case "lenovo" :
                mComputer = new LenovoComputer();
                break;
            case "asus" :
                mComputer = new AsusComputer();
                break;
        }
        return mComputer;
    }
}
