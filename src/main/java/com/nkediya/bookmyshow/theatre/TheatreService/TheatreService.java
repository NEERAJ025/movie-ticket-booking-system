package com.nkediya.bookmyshow.theatre.TheatreService;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.bookmyshow.theatre.TheatreDTO.TheatreRequest;
import com.nkediya.bookmyshow.theatre.TheatreDTO.TheatreResponse;
import com.nkediya.bookmyshow.theatre.TheatreDomain.Theatre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TheatreService {
    Theatre addTheatre(TheatreRequest theatreReq);
    Set<Movie> getMovies(City city, LocalDate date);
    List<TheatreResponse> getTheatres(City city, Movie movie, LocalDate date);
    Theatre getTheatre(String theatreName, City city);
    List<Theatre> getTheatresByCity(City city);
}
