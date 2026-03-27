package com.nkediya.bookmyshow.movie.MovieService;

import com.nkediya.bookmyshow.movie.MovieDTO.Movie;
import java.util.List;

public interface MovieService {
    public List<Movie> creteMovies(List<String> movieNames);
    Movie getMovieByName(String movieName);
}
