package com.nkediya.bookmyshow.movie.service;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import java.util.List;
import java.util.Set;

public interface MovieService {
    public List<Movie> creteMovies(List<Movie> movieNames);
    Movie getMovieByName(String movieName);
    Set<Movie> getMoviesByCity(City city);
}
