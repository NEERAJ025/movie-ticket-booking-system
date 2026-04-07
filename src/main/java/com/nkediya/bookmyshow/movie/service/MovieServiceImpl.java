package com.nkediya.bookmyshow.movie.service;

import com.nkediya.bookmyshow.common.Domain.Screen;
import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.theatre.domain.Theatre;
import com.nkediya.bookmyshow.theatre.service.TheatreService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    private Map<String, Movie> movieMap = new HashMap<>();

    private TheatreService theatreService;

    MovieServiceImpl(TheatreService theatreService){
        this.theatreService = theatreService;
    }

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

    @Override
    public Set<Movie> getMoviesByCity(City city) {
        Set<Movie> movies = new HashSet<>();
        List<Theatre> theatres = this.theatreService.getTheatresByCity(city);

        for (Theatre theatre : theatres) {
            for (Screen screen : theatre.getScreens()) {
                for (Show show : screen.getAllShows()) { // assumes all shows
                    movies.add(show.getMovie());
                }
            }
        }
        return movies;
    }
}
