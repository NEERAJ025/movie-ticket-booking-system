package com.nkediya.bookmyshow.theatre.service;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.theatre.dto.TheatreRequest;
import com.nkediya.bookmyshow.theatre.dto.TheatreResponse;
import com.nkediya.bookmyshow.theatre.domain.Theatre;

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
