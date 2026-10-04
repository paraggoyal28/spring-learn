package example.overloading;

class StackImproved {
    private int stck[];
    private int tos;

    StackImproved(int size) {
        stck = new int[size];
        tos = -1;
    }

    // Push an item onto the stack
    void push(int item) {
        if (tos == stck.length - 1) {
            System.out.println("Stack is full");
        } else {
            stck[++tos] = item;
        }
    }

    // pop an element from the stack
    int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow");
            return 0;
        } else {
            return stck[tos--];
        }
    }
}

public class TestStack2 {
    public static void main(String[] args) {
        StackImproved myStack1 = new StackImproved(5);
        StackImproved myStack2 = new StackImproved(8);

        // push some numbers onto the stack
        for (int i = 0; i < 5; ++i) {
            myStack1.push(i);
        }

        for (int i = 0; i < 8; ++i) {
            myStack2.push(i);
        }

        // pop these numbers from the stack
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
