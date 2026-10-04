package example.nestedClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class BookCollection implements Iterable<String> {
    
    private final String[] books = {"Java Concurrency In Practice", "Effective Java", "Clean Code"};

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int currentIndex = 0;
            
            @Override
            public boolean hasNext() {
                return currentIndex < books.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } 
                return books[currentIndex++];
            }
        };
    }

    public static void main(String[] args) {
        BookCollection library = new BookCollection();
        for (String book: library) {
            System.out.println(book);
        }

        library.forEach(System.out::println);
    }

}
