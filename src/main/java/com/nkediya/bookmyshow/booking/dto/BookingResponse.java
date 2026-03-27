package com.nkediya.bookmyshow.booking.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class BookingResponse {

    private UUID bookingId;
    private String userName;
    private String movieName;
    private LocalDate showDate;
    private LocalTime showTime;
    private List<Integer> seats;
    private String paymentStatus;
}
