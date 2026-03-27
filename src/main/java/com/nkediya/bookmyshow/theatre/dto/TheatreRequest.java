package com.nkediya.bookmyshow.theatre.dto;

import com.nkediya.bookmyshow.common.DTO.ScreenRequest;
import lombok.Data;

import java.util.List;

@Data
public class TheatreRequest {
    private String name;
    private String city;
    private List<ScreenRequest> screens;
}
