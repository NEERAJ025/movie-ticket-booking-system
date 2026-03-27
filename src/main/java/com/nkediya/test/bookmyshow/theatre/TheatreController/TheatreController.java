package com.nkediya.test.bookmyshow.theatre.TheatreController;

import com.nkediya.test.bookmyshow.movie.MovieService.MovieService;
import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import com.nkediya.test.bookmyshow.common.enums.City;
import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.test.bookmyshow.theatre.TheatreDTO.TheatreRequest;
import com.nkediya.test.bookmyshow.theatre.TheatreDTO.TheatreResponse;
import com.nkediya.test.bookmyshow.theatre.TheatreDomain.Theatre;
import com.nkediya.test.bookmyshow.theatre.TheatreService.TheatreService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TheatreController {

    private final TheatreService theatreService;
    private final MovieService movieService;

    public TheatreController(final TheatreService theatreService, final MovieService movieService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
    }

    @PostMapping("/add-theatre")
    public void addTheatre(@RequestBody TheatreRequest theatreRequest) {
        theatreService.addTheatre(theatreRequest);
    }

    @GetMapping("/get-theatre")
    public List<TheatreResponse> getTheatres(@RequestParam String city, @RequestParam String movieName, @RequestParam String date) {
       Movie movie = movieService.getMovieByName(movieName);
        return theatreService.getTheatres(City.valueOf(city), movie, LocalDate.parse(date));
    }

    @GetMapping("/get-all-theatre")
    public List<Theatre> getTheatresByCity(@RequestParam City city) {
        return theatreService.getTheatresByCity(city);
    }




}
