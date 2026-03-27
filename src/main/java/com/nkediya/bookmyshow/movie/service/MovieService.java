package com.nkediya.bookmyshow.movie.service;

import com.nkediya.bookmyshow.movie.dto.Movie;
import java.util.List;

public interface MovieService {
    public List<Movie> creteMovies(List<String> movieNames);
    Movie getMovieByName(String movieName);
}
