public class Student {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1 == s3);

        int a = 10;
        Integer a1 = a;
        modifyInt(a);
        System.out.println(a);
        modifyInt(b);
        System.out.println(b);
    }

    private static void modify(Employee employee){
        employee.employeeId = 20;
    }

    private static void modifyInt(Integer a) {
        a = 20;
    }

}