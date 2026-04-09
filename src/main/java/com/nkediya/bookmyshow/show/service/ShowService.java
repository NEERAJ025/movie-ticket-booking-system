package com.nkediya.bookmyshow.show.service;

import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.show.dto.ShowRequest;
import com.nkediya.bookmyshow.show.dto.ShowResponse;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.theatre.domain.Theatre;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    ShowResponse createShow(ShowRequest request);
    ShowResponse updateShow(String id, ShowRequest request);
    List<ShowResponse> getShows(Movie movie, LocalDate date, Theatre theatre);
    List<ShowResponse> browseShowsTheaterWise(City city, Movie movie, LocalDate date);
    Show getShowById(String showId);
    void deleteShow(String showId);

}
