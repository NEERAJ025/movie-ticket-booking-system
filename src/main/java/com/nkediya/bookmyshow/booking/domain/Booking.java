package com.nkediya.bookmyshow.booking.domain;

import com.nkediya.bookmyshow.common.Domain.Payment;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.user.domain.User;

import java.util.List;
import java.util.UUID;

public class Booking {
    private final UUID bookingId;
    private final User user;
    private final Show show;
    private final List<Integer> seats;
    private final Payment payment;

    public Booking(User user, Show show, List<Integer> seats, Payment payment) {
        this.bookingId = UUID.randomUUID();
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.payment = payment;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Payment getPayment() {
        return payment;
    }

    public Show getShow() {
        return show;
    }

    public List<Integer> getSeats() {
        return seats;
    }
}
