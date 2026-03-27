package com.nkediya.bookmyshow.show.ShowController;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.bookmyshow.movie.MovieService.MovieService;
import com.nkediya.bookmyshow.show.ShowDTO.ShowRequest;
import com.nkediya.bookmyshow.show.ShowDTO.ShowResponse;
import com.nkediya.bookmyshow.show.ShowDomain.Show;
import com.nkediya.bookmyshow.show.ShowService.ShowService;
import com.nkediya.bookmyshow.theatre.TheatreDomain.Theatre;
import com.nkediya.bookmyshow.theatre.TheatreService.TheatreService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowController {

    private final ShowService showService;
    private final MovieService movieService;
    private final TheatreService theatreService;

    ShowController(final ShowService showService, final MovieService movieService, final TheatreService theatreService) {
        this.showService = showService;
        this.movieService = movieService;
        this.theatreService = theatreService;
    }


    @PostMapping("/crete-show")
    public Show createShow(@RequestBody ShowRequest showReq) {
        return showService.createShow(showReq);
    }

    @GetMapping("/get-shows")
    public List<Show> getShows(@RequestParam String movieName, @RequestParam String date,
                               @RequestParam String theatreName, @RequestParam String city) {
        Movie movie = movieService.getMovieByName(movieName);
        Theatre theatre = theatreService.getTheatre(theatreName,City.valueOf(city.toUpperCase()));
        return showService.getShows(movie, LocalDate.parse(date), theatre);
    }

    @GetMapping("/get-theatre-wise-shows")
    public List<ShowResponse> getShows(@RequestParam String movieName, @RequestParam String date, @RequestParam String city) {
        Movie movie = movieService.getMovieByName(movieName);
        return showService.browseShowsTheaterWise(City.valueOf(city.toUpperCase()), movie, LocalDate.parse(date));
    }
}
