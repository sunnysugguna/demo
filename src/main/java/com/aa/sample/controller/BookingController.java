package com.aa.sample.controller;


import com.aa.sample.model.Booking;
import com.aa.sample.model.Movie;
import com.aa.sample.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/find-all-movies")
    public Collection<Movie> findAllMovies() {
        return bookingService.findAllMovies();
    }

    @PostMapping("/book-show")
    public Booking bookShow(@RequestBody Booking booking) {
        return bookingService.bookMovie(booking);
    }


}
