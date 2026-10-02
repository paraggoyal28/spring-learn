package example.accessModifierDemo;

import example.accessModifiers.StudentProtected;

public class ProtectedClassDemo extends StudentProtected {
    public static void main(String[] args) {
        ProtectedClassDemo sp = new ProtectedClassDemo();
        System.out.println(sp.age);
    }    
}
