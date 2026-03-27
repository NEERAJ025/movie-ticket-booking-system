package com.nkediya.bookmyshow.booking.BookingController;

import com.nkediya.bookmyshow.booking.BookingDTO.BookingRequest;
import com.nkediya.bookmyshow.booking.BookingDTO.BookingResponse;
import com.nkediya.bookmyshow.booking.BookingDomain.Booking;
import com.nkediya.bookmyshow.booking.BookingService.BookingService;
import com.nkediya.bookmyshow.show.ShowDomain.Show;
import com.nkediya.bookmyshow.show.ShowService.ShowService;
import com.nkediya.bookmyshow.user.UserDomain.User;
import com.nkediya.bookmyshow.user.UserService.UserService;
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

    @PostMapping("/create-bookings")
    public Booking createBooking(@RequestBody BookingRequest bookingRequest) {
        User user = userService.getById(bookingRequest.getUserId());
        Show show = showService.getShowById (bookingRequest.getShowId());

        Booking booking = bookingService.book(user, show, bookingRequest.getSeatIds());
        return booking;
    }

    @GetMapping("/booking-details")
    public BookingResponse getBookingDeatils(@RequestParam UUID bookingId) {
        return bookingService.getBookingDetailsWithId(bookingId);
    }

    public List<Booking> getBookingsForUser(User user) {
        return bookingService.getBookingsForUser(user);
    }

}
