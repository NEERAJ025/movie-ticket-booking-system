package com.nkediya.test.bookmyshow.show.ShowService;

import com.nkediya.test.bookmyshow.common.enums.City;
import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.test.bookmyshow.show.ShowDTO.ShowRequest;
import com.nkediya.test.bookmyshow.show.ShowDTO.ShowResponse;
import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import com.nkediya.test.bookmyshow.theatre.TheatreDomain.Theatre;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    Show createShow(ShowRequest request);
    List<Show> getShows(Movie movie, LocalDate date, Theatre theatre);
    List<ShowResponse> browseShowsTheaterWise(City city, Movie movie, LocalDate date);
    Show getShowById(String showId);

}
