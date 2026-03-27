package com.nkediya.test.bookmyshow.theatre.TheatreDTO;

import com.nkediya.test.bookmyshow.common.DTO.ScreenRequest;
import lombok.Data;

import java.util.List;

@Data
public class TheatreRequest {
    private String name;
    private String city;
    private List<ScreenRequest> screens;
}
