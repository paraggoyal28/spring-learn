package example.overloading;

class RecTest {
    int values[];

    RecTest(int i) {
        values = new int[i];
    }

    // display array - recursively
    void printArray(int i) {
        if (i == 0) return;
        System.out.println("[" + (i-1) + "]" + values[i-1]);
        printArray(i-1);
        //System.out.println("[" + (i-1) + "] " + values[i-1]);
    }
}


public class Recursion2 {
    public static void main(String[] args) {
        RecTest obj = new RecTest(10);
        int i;

        for (i = 0; i < 10; ++i) obj.values[i] = i;

        obj.printArray(10);
    }    
}
