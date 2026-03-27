package com.nkediya.test.bookmyshow.movie.MovieService;

import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import java.util.List;

public interface MovieService {
    public List<Movie> creteMovies(List<String> movieNames);
    Movie getMovieByName(String movieName);
}
