package com.nkediya.test.bookmyshow.theatre.TheatreService;

import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import com.nkediya.test.bookmyshow.common.enums.City;
import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.test.bookmyshow.theatre.TheatreDTO.TheatreRequest;
import com.nkediya.test.bookmyshow.theatre.TheatreDTO.TheatreResponse;
import com.nkediya.test.bookmyshow.theatre.TheatreDomain.Theatre;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TheatreService {
    Theatre addTheatre(TheatreRequest theatreReq);
    Set<Movie> getMovies(City city, LocalDate date);
    List<TheatreResponse> getTheatres(City city, Movie movie, LocalDate date);
    Theatre getTheatre(String theatreName, City city);
    List<Theatre> getTheatresByCity(City city);
}
