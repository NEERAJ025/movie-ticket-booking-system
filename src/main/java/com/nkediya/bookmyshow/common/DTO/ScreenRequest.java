package com.nkediya.bookmyshow.common.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ScreenRequest {
    private int screenId;
    private List<SeatInfo> seatInfo;
}
