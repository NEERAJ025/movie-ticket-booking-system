package com.nkediya.bookmyshow.theatre.controller;

import com.nkediya.bookmyshow.movie.service.MovieService;
import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.theatre.dto.TheatreRequest;
import com.nkediya.bookmyshow.theatre.dto.TheatreResponse;
import com.nkediya.bookmyshow.theatre.domain.Theatre;
import com.nkediya.bookmyshow.theatre.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TheatreController {

    private final TheatreService theatreService;
    private final MovieService movieService;

    public TheatreController(final TheatreService theatreService, final MovieService movieService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
    }

    @PostMapping("/theatres")
    public ResponseEntity<TheatreResponse> addTheatre(@RequestBody TheatreRequest theatreRequest) {
        Theatre theatre = theatreService.addTheatre(theatreRequest);
        if (theatreRequest.getScreens() == null || theatreRequest.getScreens().isEmpty()) {
            throw new IllegalArgumentException("At least one screen is required");
        }else{
            TheatreResponse response = TheatreResponse.builder().city(theatre.getCity().name()).name(theatre.getName()).totalScreens(theatre.getScreens().size()).build();
            return  ResponseEntity.status(HttpStatus.CREATED).body(response);

        }

    }

    @GetMapping("/theatres")
    public List<TheatreResponse> getTheatres(@RequestParam String city, @RequestParam String movieName, @RequestParam String date) {
       Movie movie = movieService.getMovieByName(movieName);
        return theatreService.getTheatres(City.valueOf(city), movie, LocalDate.parse(date));
    }

    @GetMapping("/theatres/{city}")
    public List<TheatreResponse> getTheatresByCity(@PathVariable("city") City city) {
        List<Theatre> theatres = theatreService.getTheatresByCity(city);
        return theatres.stream().map(theatre -> TheatreResponse.builder().city(theatre.getCity().name()).name(theatre.getName())
                .totalScreens(theatre.getScreens().size()).build()).toList();

    }




}
