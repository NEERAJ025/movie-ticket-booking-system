package com.nkediya.bookmyshow.show.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequest {
    private String movieName;
    private String theatreName;
    private String city;
    private int screenId;
    private LocalDate date;
    private LocalTime time;
}
