package com.nkediya.test.bookmyshow.movie.MovieDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private String id;
    private  String name;
    private String durationInMinutes;
    private String rating;
}
