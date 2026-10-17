package example.exception;

public class UncheckedExceptionExample {

    private static void print(String text) {
        System.out.println(text.length());


    }

    public static void main(String[] args) {
        String text = null;

        print(text);
    }
}
