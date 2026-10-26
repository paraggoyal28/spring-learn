package Banking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractionExample {
    public static void main(String[] args) {
        String logText = "Error code: 404 occurred at 12:45 PM. Urgent fix needed for ID: 89312";

        // Matches any sequence of 3 or more consecutive digits
        Pattern pattern = Pattern.compile("\\d{3,}");
        Matcher matcher = pattern.matcher(logText);

        System.out.println("Found numbers: ");
        while (matcher.find()) {
            // matcher group() retrieves the text matched by current find()
            System.out.println(" " + matcher.group());
        }

        String sentence = "Java, 1s  very:powerful";

        // Split by commas, semicolons, or spaces, appearing one or more times
        String[] words = sentence.split("[,; ]+");

        for (String word: words) {
            System.out.println("word: " + word);
        }

        Pattern pattern2 = Pattern.compile("(java)");
        Matcher matcher1 = pattern.matcher("I love java");
        if (matcher1.find()) {
            System.out.println(matcher1.start());
            System.out.println(matcher1.end());
            System.out.println(matcher1.start(1));
            System.out.println(matcher1.end(1));
        }

        Pattern pattern3 = Pattern.compile("(java)(\\d)");
        Matcher matcher2 = pattern3.matcher("java5");
        if (matcher2.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.groupCount());
            System.out.println(matcher.hitEnd());
            System.out.println(matcher.requireEnd());
        }
    }
}
