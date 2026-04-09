package com.nkediya.bookmyshow.theatre.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TheatreResponse {
    private String name;
    private String city;
    private int totalScreens;

}
