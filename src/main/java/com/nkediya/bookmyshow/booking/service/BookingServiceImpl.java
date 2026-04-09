package com.nkediya.bookmyshow.booking.service;

import com.nkediya.bookmyshow.booking.dto.BookingResponse;
import com.nkediya.bookmyshow.booking.domain.Booking;
import com.nkediya.bookmyshow.common.Domain.Payment;
import com.nkediya.bookmyshow.common.exception.SeatUnavailableException;
import com.nkediya.bookmyshow.pricing.service.PricingService;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.common.enums.PaymentStatus;
import com.nkediya.bookmyshow.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService{
    private final Map<UUID, Booking> bookings = new HashMap<>();

    private final PricingService pricingService;

    BookingServiceImpl(PricingService pricingService){
        this.pricingService = pricingService;
    }

    @Override
    public BookingResponse book(User user, Show show, List<Integer> seats) {
        if (!show.lockSeats(seats)) {
            throw new SeatUnavailableException("Seats already booked or locked");
        }

        //simulated payment flow here, we can invoke Pay method of Payment Controller
        double totalAmount = pricingService.calculateTotalPrice(show, seats);
        Payment payment = new Payment(PaymentStatus.SUCCESS, totalAmount) ;

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            show.confirmSeats(seats);
            Booking booking =  new Booking(user, show, seats, payment);
            bookings.put(booking.getBookingId(), booking);

             return BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .userName(booking.getUser().getName())
                    .movieName(booking.getShow().getMovie().getName())
                    .showDate(booking.getShow().getShowDate())
                    .showTime(booking.getShow().getStartTime())
                    .seats(booking.getSeats())
                    .paymentStatus(booking.getPayment().getStatus().name())
                     .totalAmount(booking.getPayment().getAmount())
                    .build();
        } else {
            show.releaseSeats(seats);
            throw new RuntimeException("Payment failed");
        }

    }

    @Override
    public BookingResponse getBookingDetailsWithId(UUID bookingId) {
        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }

        Show show = booking.getShow();

        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .userName(booking.getUser().getName())
                .movieName(show.getMovie().getName())
                .showDate(show.getShowDate())
                .showTime(show.getStartTime())
                .seats(booking.getSeats())
                .paymentStatus(booking.getPayment().getStatus().name())
                .totalAmount(booking.getPayment().getAmount())
                .build();
    }

    @Override
    public List<Booking> getBookingsForUser(User user) {
        return bookings.values()
                .stream()
                .filter(b -> b.getUser().equals(user))
                .toList();
    }

}
