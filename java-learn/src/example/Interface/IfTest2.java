package example.Interface;

class DynStack implements IntStack {
    private int stck[];
    private int tos;

    // allocate and initialize stack
    DynStack(int size) {
        stck = new int[size];
        tos = -1;
    }

    // push an item onto the stack
    public void push(int item) {
        // if stack is full, allocate a larger stack
        if (tos == stck.length-1) {
            int temp[] = new int[stck.length * 2]; // double size
            for (int i = 0; i < stck.length; ++i) temp[i] = stck[i];
            stck = temp;
            stck[++tos] = item;
        } else {
            stck[++tos] = item;
        }
    }

    // Pop an item from the stack
    public int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow");
            return 0;
        } else {
            return stck[tos--];
        }
    }
}

public class IfTest2 {
    public static void main(String[] args) {
        DynStack myStack1 = new DynStack(5);
        DynStack myStack2 = new DynStack(8);

        // these loops cause each stack to grow
        for (int i = 0; i < 5; ++i) {
            myStack1.push(i);
        }
        for (int i = 0; i < 8; ++i) {
            myStack2.push(i);
        }

        System.out.println("Stack in myStack1: ");
        for (int i = 0; i < 5; ++i) {
            System.out.println(myStack1.pop());
        }

        System.out.println("Stack in myStack2: ");
        for (int i = 0; i < 8; ++i) {
            System.out.println(myStack2.pop());
        }
    }    
}
