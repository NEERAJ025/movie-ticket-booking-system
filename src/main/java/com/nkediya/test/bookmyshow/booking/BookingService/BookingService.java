package com.nkediya.test.bookmyshow.booking.BookingService;

import com.nkediya.test.bookmyshow.booking.BookingDTO.BookingResponse;
import com.nkediya.test.bookmyshow.booking.BookingDomain.Booking;
import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import com.nkediya.test.bookmyshow.user.UserDomain.User;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking book(User user, Show show, List<Integer> seats);
    BookingResponse getBookingDetailsWithId(UUID bookingId);
    List<Booking> getBookingsForUser(User user);
}
