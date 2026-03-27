package com.nkediya.test.bookmyshow.movie.MovieController;

import com.nkediya.test.bookmyshow.common.enums.City;
import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.test.bookmyshow.movie.MovieService.MovieService;
import com.nkediya.test.bookmyshow.theatre.TheatreService.TheatreService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;
    private final TheatreService theatreService;

    MovieController(MovieService movieService, TheatreService theatreService){
        this.movieService = movieService;
        this.theatreService = theatreService;
    }

    @PostMapping("/add-movies")
    public List<Movie> createMovies(@RequestBody List<String> movieNames) {
        return movieService.creteMovies(movieNames);
    }

    @GetMapping("/find-movies")
    public Set<Movie> findMovies(@RequestParam City city, @RequestParam String date) {
        return theatreService.getMovies(city, LocalDate.parse(date));
    }
}
