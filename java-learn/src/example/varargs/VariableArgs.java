class PassArray {
    static void vaTest(int v[]) {
        System.out.println("Number of args: " + v.length +  "Contents: ");

        for (int x: v) {
            System.out.println(x + " ");
        }

        System.out.println();
    }

    public static void main(String args[]) {
        int n1[] = {10};
        int n2[] = {1, 2, 3};
        int n3[] = {};

        vaTest(n1);
        vaTest(n2);
        vaTest(n3);
    }
}

class VarArgs {
    static void vaTest(int ... v) {
        System.out.println("Number of args " + v.length +   
            " Contents: ");
        
        for (int x: v) {
            System.out.println(x + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        vaTest(10);
        vaTest(1, 2, 3);
        vaTest();
    }
}

/*

the varargs parameter must be last. For example, the following declaration is incorrect
int doIt(int a, int b, double c, int ... vals, boolean stopFlag);

This declaration is correct though
int doIt(int a, int b, double c, int ... vals);

Another restriction is to be aware of: there must be only one varargs parameter.
For example, this declaration is also invalid:

int doIt(int a, int b, double c, int ... vals, double ... moreVals); // Error

*/

class VarArgsDemo {
    static void vaTest(String msg, int ... v) {
        System.out.println(msg + v.length + " Contents: ");
        for (int x: v) {
            System.out.print(x + " "); 
        }

        System.out.println();
    }

    public static void main(String args[]) {
        vaTest("One vararg: " , 10);
        vaTest("Three varargs: ", 1, 2, 3);
        vaTest("No varargs: ");
    }
}

// Overloading varargs methods

class VarArgs {
    static void vaTest(int ... v) {
        System.out.println("vaTest(int ... ) : " + "Number of args: " + v.length + " Contents: ");

        for (int x: v) {
            System.out.print(x + " ");
        }

        System.out.println();
    }

    static void vaTest(boolean ... v) {
        System.out.println("vaTest(boolean ...) : " + "Number of args: " + v.length + " Contents: ");

        for (boolean x: v) {
            System.out.print(x + " ");
        } 

        System.out.println();
    }

    static void vaTest(String msg, int ... v) {
        System.out.println("vaTest(String, int ...) : " +  msg + v.length + "Contents: ");
        for (int x : v) {
            System.out.print(x + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        vaTest(1, 2, 3);
        vaTest("Testing...", 10, 20);
        vaTest(true, false, true);
    }
}
/* Two ways to overload varargs method
1. Different types of array parameters
2. Different types of varargs.


vaTest(int v) is a valid overload of vaTest(... v) for above methods
*/

// Ambiguity

class VarArgs {
    static void vaTest(int ... v) {
        System.out.print("vaTest(int ... ) " + "Number of args: " + v.length + " Contents: " );
        for (int x: v) {
            System.out.print(x + " ");
        }

        System.out.println();
    }

    static void vaTest(boolean ... v) {
        System.out.print("vaTest(boolean ... ) " + " Number of args: " + v.length + " Contents: ");
        for (boolean x: v) { 
            System.out.print(x + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        vaTest(1, 2, 3, 4); // ok
        vaTest(true, false, false); // ok
        vaTest(); // ambiguous cannot resolve to vaTest(int ...) or vaTest(boolean ...)
    }
}

// Another ambiguity
/*

static void vaTest(int ... v);
static void vaTest(int x, int ... v); 

Now vaTest(1) will be resolved by which method
*/
