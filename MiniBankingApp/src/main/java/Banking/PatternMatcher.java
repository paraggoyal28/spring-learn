package Banking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static void main(String[] args) {
        String logText = "Error code: 404 occurred at 12:45 PM. Urgent fix needed for ID: 89312";

        // Match any sequence of 3 or more consecutive digits
        Pattern pattern = Pattern.compile("\\d{3,}");
        Matcher matcher = pattern.matcher(logText);

        System.out.println("Found numbers: ");
        while (matcher.find()) {
            // matcher.group() retrieves the text method by the current find()
            System.out.println(" " + matcher.group());
        }
    }
}
