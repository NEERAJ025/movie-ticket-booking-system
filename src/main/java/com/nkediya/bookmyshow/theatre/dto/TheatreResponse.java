package com.nkediya.bookmyshow.theatre.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheatreResponse {
    private String name;
    private String city;
}
