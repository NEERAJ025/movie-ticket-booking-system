package com.nkediya.bookmyshow.common.DTO;

import com.nkediya.bookmyshow.common.enums.SeatCategory;
import lombok.Data;

@Data
public class SeatInfo {
     private SeatCategory category;
        private int count;
    }

