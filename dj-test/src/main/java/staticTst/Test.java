package staticTst;

public class Test {
    public static int a = 0;

    public static void main(String[] args) {
        Test a = new Test();
        Test b = new Test();
        a.a = 100;
        System.out.println(a.a);
        System.out.println(b.a);

    }
}
