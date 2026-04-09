package com.nkediya.bookmyshow.booking.service;

import com.nkediya.bookmyshow.booking.dto.BookingResponse;
import com.nkediya.bookmyshow.booking.domain.Booking;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingResponse book(User user, Show show, List<Integer> seats);
    BookingResponse getBookingDetailsWithId(UUID bookingId);
    List<Booking> getBookingsForUser(User user);
}
