package com.nkediya.test.bookmyshow.theatre.TheatreDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheatreResponse {
    private String name;
    private String city;
}
