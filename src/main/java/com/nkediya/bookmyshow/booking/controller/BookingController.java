package com.nkediya.bookmyshow.booking.controller;

import com.nkediya.bookmyshow.booking.dto.BookingRequest;
import com.nkediya.bookmyshow.booking.dto.BookingResponse;
import com.nkediya.bookmyshow.booking.domain.Booking;
import com.nkediya.bookmyshow.booking.service.BookingService;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.show.service.ShowService;
import com.nkediya.bookmyshow.user.domain.User;
import com.nkediya.bookmyshow.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final ShowService showService;

    BookingController(final BookingService bookingService, final UserService userService,
                      final ShowService showService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.showService = showService;
    }

    @PostMapping("/bookings")
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest) {
        User user = userService.getById(bookingRequest.getUserId());
        Show show = showService.getShowById (bookingRequest.getShowId());
        return bookingService.book(user, show, bookingRequest.getSeatIds());
    }

    @GetMapping("/bookings/{id}")
    public BookingResponse getBookingDetails(@PathVariable("id") UUID bookingId) {
        return bookingService.getBookingDetailsWithId(bookingId);
    }

    public List<Booking> getBookingsForUser(User user) {
        return bookingService.getBookingsForUser(user);
    }

}
