package com.nkediya.bookmyshow.show.controller;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.movie.service.MovieService;
import com.nkediya.bookmyshow.show.dto.ShowRequest;
import com.nkediya.bookmyshow.show.dto.ShowResponse;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.show.service.ShowService;
import com.nkediya.bookmyshow.theatre.domain.Theatre;
import com.nkediya.bookmyshow.theatre.service.TheatreService;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/shows")
    public ShowResponse createShow(@RequestBody ShowRequest showReq) {
        return showService.createShow(showReq);
    }

    @PutMapping("/shows/{showId}")
    public ShowResponse updateShow(@PathVariable String showId, @RequestBody ShowRequest showReq) {
        return showService.updateShow(showId, showReq);
    }

    @GetMapping("/shows")
    public List<ShowResponse> getShows(@RequestParam String movieName, @RequestParam String date,
                               @RequestParam String theatreName, @RequestParam String city) {
        Movie movie = movieService.getMovieByName(movieName);
        Theatre theatre = theatreService.getTheatre(theatreName,City.valueOf(city.toUpperCase()));
        return showService.getShows(movie, LocalDate.parse(date), theatre);
    }

    @DeleteMapping("/show/{showId}")
    public ResponseEntity<String> deleteShow(@PathVariable String showId) {
        showService.deleteShow(showId);
        return ResponseEntity.ok("Show deleted successfully");
    }

//    @GetMapping("/shows")
//    public List<ShowResponse> browseShowsTheaterWise(@RequestParam String movieName, @RequestParam String date, @RequestParam String city) {
//        Movie movie = movieService.getMovieByName(movieName);
//        return showService.browseShowsTheaterWise(City.valueOf(city.toUpperCase()), movie, LocalDate.parse(date));
//    }
}
