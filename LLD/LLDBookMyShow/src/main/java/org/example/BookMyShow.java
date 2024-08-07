package org.example;


import org.example.Enums.City;
import org.example.Enums.SeatCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;

    BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();

        // user1
        bookMyShow.createBooking(City.Bangalore, "BAAHUBALI");
        // user 2
        bookMyShow.createBooking(City.Bangalore, "BAAHUBALI");

    }

    private void createBooking(City city, String movieName) {
        // search movie by my location
        List<Movie> movies = movieController.getMoviesByCity(city);

        Movie interestedMovie = null;
        for(Movie movie: movies) {
            if((movie.getMovieName()).equals(movieName)) {
                interestedMovie = movie;
                break;
            }
        }

        // get all show of this movie in current location
        Map<Theatre, List<Show>> showsTheatreWise = theatreController.getAllShow(interestedMovie, city);

        // select the particular show user is interested in
        Map.Entry<Theatre, List<Show>> entry = showsTheatreWise.entrySet().iterator().next();
        List<Show> runningShows = entry.getValue();
        Show interestedShow = runningShows.get(0);

        // select the seat
        int seatNumber = 30;
        List<Integer> bookedSeats = interestedShow.getBookedSeatIds();
        if(!bookedSeats.contains(seatNumber)) {
            bookedSeats.add(seatNumber);

            // start Payment
            Booking booking = new Booking();
            List<Seat> myBookedSeats = new ArrayList<>();
            for(Seat screenSeat: interestedShow.getScreen().getSeats()) {
                if(screenSeat.getSeatId() == seatNumber) {
                    myBookedSeats.add(screenSeat);
                }
            }
            booking.setBookedSeats(myBookedSeats);
            booking.setShow(interestedShow);
        } else {
            System.out.println("seat already booked, try again");
            return;
        }

        System.out.println("Booking Successful");
    }


    private void initialize() {
        createMovies();
        createTheatre();

    }

    private void createTheatre() {
        Movie avengerMovie = movieController.getMovieByName("AVENGERS");
        Movie bahubali = movieController.getMovieByName("BAAHUBALI");

        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setTheatreId(1);
        inoxTheatre.setScreens(createScreen());
        inoxTheatre.setCity(City.Bangalore);
        List<Show> inoxShows = new ArrayList<>();
        Show inoxMorningShow = createShows(1, inoxTheatre.getScreens().get(0), avengerMovie, 8);
        Show inoxEveningShow = createShows(2, inoxTheatre.getScreens().get(0), bahubali, 16);
        inoxShows.add(inoxMorningShow);
        inoxShows.add(inoxEveningShow);
        inoxTheatre.setShows(inoxShows);

        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setTheatreId(2);
        pvrTheatre.setScreens(createScreen());
        pvrTheatre.setCity(City.Delhi);
        List<Show> pvrShows = new ArrayList<>();
        Show pvrMorningShow = createShows(3, pvrTheatre.getScreens().get(0), avengerMovie, 13);
        Show pvrEveningShow = createShows(4, pvrTheatre.getScreens().get(0), bahubali, 20);
        pvrShows.add(pvrMorningShow);
        pvrShows.add(pvrEveningShow);
        pvrTheatre.setShows(pvrShows);

        theatreController.addTheatre(inoxTheatre, City.Bangalore);
        theatreController.addTheatre(pvrTheatre, City.Delhi);


    }

    private Show createShows(int showId, Screen screen, Movie movie, int showStartTime) {
        Show show = new Show();
        show.setShowId(showId);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setShowStartTime(showStartTime);
        return show;
    }

    private List<Screen> createScreen() {
        List<Screen> screens = new ArrayList<>();
        Screen screen = new Screen();
        screen.setScreenId(1);
        screen.setSeats(createSeats());
        screens.add(screen);

        return screens;
    }

    private List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();
        //1 to 40 : SILVER
        for (int i = 0; i < 40; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }

        //41 to 70 : SILVER
        for (int i = 40; i < 70; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.GOLD);
            seats.add(seat);
        }

        //1 to 40 : SILVER
        for (int i = 70; i < 100; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.PLATINUM);
            seats.add(seat);
        }

        return seats;

    }


    private void createMovies() {
        // create movie1
        Movie avengers = new Movie();
        avengers.setMovieId(1);
        avengers.setMovieName("AVENGERS");
        avengers.setMovieDurationInMinutes(128);

        Movie bahubali = new Movie();
        bahubali.setMovieId(2);
        bahubali.setMovieName("BAAHUBALI");
        bahubali.setMovieDurationInMinutes(180);

        movieController.addMovie(avengers, City.Bangalore);
        movieController.addMovie(avengers, City.Delhi);
        movieController.addMovie(bahubali, City.Bangalore);
        movieController.addMovie(bahubali, City.Delhi);
    }
}
