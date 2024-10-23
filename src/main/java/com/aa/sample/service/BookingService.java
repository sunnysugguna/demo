package com.aa.sample.service;

import com.aa.sample.model.Booking;
import com.aa.sample.model.Movie;
import com.aa.sample.model.Seat;
import com.aa.sample.model.Showtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookingService {

    private final Collection<Movie> movies = new ArrayList<>();
    private final Collection<Booking> bookings = new ArrayList<>();
    private final AtomicLong bookingIdCounter = new AtomicLong(1);

    @Autowired
    public BookingService() {
        loadMovies();
    }

    public Collection<Movie> findAllMovies() {
        return movies;
    }

    public Booking bookMovie(Booking booking) {

        var bookingId = bookingIdCounter.getAndAdd(1L);
        booking.setId(bookingId);

        for (Movie movie : movies) {
            if (movie.getId() == booking.getMovieId()) {
                for(Showtime showtime : movie.getShowTimes()) {
                    if (showtime.getShowId() == booking.getShowId()) {
                        for(Seat seat : showtime.getSeats()) {
                            if (seat.getNumber().equals(booking.getSeatNumber())) {
                                if (seat.isOccupied()) {
                                    throw new IllegalArgumentException("Seat is already booked");
                                }
                                seat.setOccupied(true);
                            }
                        }
                    }
                }
            }
        }

        bookings.add(booking);
        return booking;
    }

    private void loadMovies() {
        var movie = Movie.builder().id(1).name("something").build();
        var showTimes = new ArrayList<Showtime>();

        for (int i = 0; i <= 5; i++) {
            var showtime = Showtime.builder().showId(i).time(LocalDateTime.now().plusHours(i)).build();
            showtime.setSeats(initializeSeats());
            showTimes.add(showtime);
        }
        movie.setShowTimes(showTimes);
        movies.add(movie);

    }

    private Collection<Seat> initializeSeats() {
        var seats = new ArrayList<Seat>();
        for (int i = 0; i <= 10; i++) {
            seats.add(Seat.builder().number(String.valueOf(i)).build());
        }
        return seats;
    }



}
