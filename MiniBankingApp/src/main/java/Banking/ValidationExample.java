package Banking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationExample {
    public static void main(String[] args) {
        String email = "user@example.com";

        // Simple regex pattern for basic email verification
        String regex = "^[A-Za-z0-9._+-}+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            System.out.println("Valid email address!");
        } else {
            System.out.println("Invalid email address!");
        }
    }
}
