package example.TryWithResourcesDemo;


import java.util.List;

public class EffectivelyFinalDemo {
    public static void main(String[] args) {
        int threshold = 50; // Effectively final

        List<Integer> numbers = List.of(10, 60, 30, 80);
        // Works perfectly because 'threshold' never changes
        numbers.stream().filter(n -> n > threshold).forEach(System.out::println); 

        // if we uncomment below line then the code will not compile
        // threshold needs to be effectively final
        // threshold = 90;

    } 
}
