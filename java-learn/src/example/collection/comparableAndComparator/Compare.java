
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Movie implements Comparable<Movie> {
    private double rating;
    private String name;
    private int year;

    // Used to sort movies by year
    public int compareTo(Movie m) {
        return this.year - m.year;
    }

    // Constructor
    public Movie(String nm, double rt, int yr) {
        this.name = nm;
        this.rating = rt;
        this.year = yr;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
}

class RatingCompare implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
        if(m1.getRating() < m2.getRating()) {
            return -1;
        } else if(m1.getRating() > m2.getRating()) {
            return 1;
        }
        return 0;
    }
}

class NameCompare implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
        return m1.getName().compareTo(m2.getName());
    }
}


public class Compare {
    public static void main(String[] args) {
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(new Movie("Force Awakens", 8.3, 2015));
        list.add(new Movie("Star Wars", 8.7, 1977));
        list.add(new Movie("Empire Strikes Back", 8.8, 1980));
        list.add(new Movie("Return of the jedi", 8.4, 1983));

        // Sort by rating
        System.out.println("Sorted by rating:");
        Collections.sort(list, new RatingCompare());

        for(Movie movie : list) {
            System.out.println(movie.getRating() + " " + movie.getName() + " " + movie.getYear());
        }

        // Sort by name
        System.out.println("\nSorted by name");
        Collections.sort(list, new NameCompare());
        for(Movie movie : list) {
            System.out.println(movie.getRating() + " " + movie.getName() + " " + movie.getYear());
        }

        // Use comparable to sort by year
        System.out.println("\nSorted by year");
        Collections.sort(list);
        for(Movie movie : list) {
            System.out.println(movie.getRating() + " " + movie.getName() + " " + movie.getYear());
        }
    }
}
