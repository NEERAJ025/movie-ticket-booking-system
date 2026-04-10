package com.nkediya.bookmyshow.booking.entity;

import com.nkediya.bookmyshow.common.enums.PaymentStatus;
import com.nkediya.bookmyshow.show.entity.Show;
import com.nkediya.bookmyshow.user.entity.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    private UUID bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;


    @ElementCollection
    private List<Integer> seats;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private double totalAmount;
}