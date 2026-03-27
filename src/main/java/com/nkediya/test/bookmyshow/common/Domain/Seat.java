package com.nkediya.test.bookmyshow.common.Domain;

import com.nkediya.test.bookmyshow.common.enums.SeatCategory;
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
