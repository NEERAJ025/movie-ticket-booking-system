package com.nkediya.bookmyshow.movie.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private String id;
    private String name;
    private String durationInMinutes;
    private String rating;
    private String languages;
    private String genres;
}
