package com.nkediya.bookmyshow.common.Domain;

import com.nkediya.bookmyshow.common.enums.SeatCategory;
import lombok.Getter;

@Getter
public class Seat {
    private final int seatId;
    private final SeatCategory category;

    public Seat(int seatId, SeatCategory category) {
        this.seatId = seatId;
        this.category = category;
    }

}
