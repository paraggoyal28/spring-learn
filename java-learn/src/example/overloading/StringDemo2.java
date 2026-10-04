package example.overloading;


public class StringDemo2 {
    public static void main(String[] args) {
        String strObj1 = "First String";
        String strObj2 = "Second String";
        String strObj3 = strObj1;

        System.out.println("Length of strObj1: " + strObj1.length());

        System.out.println("Char at index 3 of strObj1 is: " + strObj1.charAt(3));

        if (strObj1.equals(strObj2)) {
            System.out.println("strObj1 == strObj2");
        } else {
            System.out.println("strObj1 != strObj2");
        }

        if (strObj1.equals(strObj3)) {
            System.out.println("strObj1 == strObj3");
        } else {
            System.out.println("strObj1 != strObj3");
        }
    }    
}
