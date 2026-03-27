package com.nkediya.bookmyshow.booking.BookingDTO;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private String userId;
    private String showId;
    private List<Integer> seatIds;
}
