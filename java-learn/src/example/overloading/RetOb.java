package example.overloading;


class Test {
    int a;
    Test(int a) {
        this.a = a;
    }

    Test incrByTen() {
        Test temp = new Test(a + 10);
        return temp;
    }
}


public class RetOb {
    public static void main(String[] args) {
        Test obj1 = new Test(2);
        Test obj2;

        obj2 = obj1.incrByTen();
        System.out.println("obj1.a: " + obj1.a);
        System.out.println("obj2.a: " + obj2.a);

        obj2 = obj2.incrByTen();
        System.out.println("obj2.a: " + obj2.a);
    }
}
