package example.newFeatures;

import java.util.List;

public class LocalRecordDemo {
    public static void main(String[] args) {
        List<String> words = List.of("Java", "Records", "Stream", "Code", "Clean");

        // Define a local record inside main
        record WordLengthStats(String word, int length) {}

        // Use the local record to a Stream transformation
        List<WordLengthStats> stats = words.stream()
                                        .map(w -> new WordLengthStats(w, w.length()))
                                        .toList();
                            
        stats.forEach(s -> System.out.println("Word: " + s.word() + " Length: " + s.length()));
    }
}
