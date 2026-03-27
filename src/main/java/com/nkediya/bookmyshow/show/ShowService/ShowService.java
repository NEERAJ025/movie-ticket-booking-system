package com.nkediya.bookmyshow.show.ShowService;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.bookmyshow.show.ShowDTO.ShowRequest;
import com.nkediya.bookmyshow.show.ShowDTO.ShowResponse;
import com.nkediya.bookmyshow.show.ShowDomain.Show;
import com.nkediya.bookmyshow.theatre.TheatreDomain.Theatre;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    Show createShow(ShowRequest request);
    List<Show> getShows(Movie movie, LocalDate date, Theatre theatre);
    List<ShowResponse> browseShowsTheaterWise(City city, Movie movie, LocalDate date);
    Show getShowById(String showId);

}
