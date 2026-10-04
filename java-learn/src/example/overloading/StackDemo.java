package example.overloading;

class Stack {
    private int stck[] = new int[10];
    private int tos;

    Stack() {
        tos = -1;
    }

    void push(int item) {
        if (tos == 9) {
            System.out.println("Stack is full");
        } else {
            stck[++tos] = item;
        }
    }

    int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow");
            return 0;
        } 
        
        return stck[tos--];
    }
}


public class StackDemo {
    public static void main(String[] args) {
        Stack myStack1 = new Stack();
        Stack myStack2 = new Stack();

        // Push some elements onto the stack
        for (int i = 0; i < 10; ++i) myStack1.push(i);
        for (int i = 10; i < 20; ++i) myStack2.push(i);

        // pop these numbers from the stack
        System.out.println("Elements in myStack1: ");
        for (int i = 0; i < 10; ++i) {
            System.out.println(myStack1.pop());
        }

        System.out.println("Elements in myStack2: ");
        for (int i = 10; i < 20; ++i) {
            System.out.println(myStack2.pop());
        }

        // these statements are not legal
        // myStack2.tos = 2;
        // myStack.stck[2] = 90;
    }
}
