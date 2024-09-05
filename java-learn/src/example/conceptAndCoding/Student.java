public class Student {
    public static void main(String[] args) {
        int ans = add("Hello", 1, 2, 3, 4);
        System.out.println("Answer is " + ans);
    }

    private static int add(String name, int ...variables){
        int ans = 0;
        for(int var: variables) {
            ans += var;
        }
        return ans;
    }

    private static void modifyInt(Integer a) {
        a = 20;
    }

}