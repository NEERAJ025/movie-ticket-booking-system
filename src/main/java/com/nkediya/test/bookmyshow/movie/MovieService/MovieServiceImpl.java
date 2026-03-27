package com.nkediya.test.bookmyshow.movie.MovieService;

import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    private Map<String, Movie> movieMap = new HashMap<>();

    @Override
    public List<Movie> creteMovies(List<String> movieNames) {
        List<Movie> movies = new ArrayList<>();
        for(String movieName : movieNames){
            Movie movie =  Movie.builder().id(UUID.randomUUID().toString())
                    .name(movieName).build();
            movieMap.put(movie.getName().toLowerCase(),movie);
            movies.add(movie);
        }
        return movies;

    }

    @Override
    public Movie getMovieByName(String movieName) {
        return Optional.ofNullable(movieMap.get(movieName.toLowerCase()))
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
