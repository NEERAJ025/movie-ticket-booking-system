package com.nkediya.bookmyshow.show.service;

import com.nkediya.bookmyshow.common.Domain.Screen;
import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.common.enums.SeatStatus;
import com.nkediya.bookmyshow.common.exception.ResourceNotFoundException;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.movie.service.MovieService;
import com.nkediya.bookmyshow.show.dto.ShowRequest;
import com.nkediya.bookmyshow.show.dto.ShowResponse;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.theatre.domain.Theatre;
import com.nkediya.bookmyshow.theatre.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    MovieService movieService;

    @Autowired
    TheatreService theatreService;

    private final Map<String, Show> showMap = new HashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger();

    @Override
    @CacheEvict(value = "shows", allEntries = true)
    public ShowResponse createShow(ShowRequest showRequest) {
        Movie movie = movieService.getMovieByName(showRequest.getMovieName());
        City city = City.valueOf(showRequest.getCity().toUpperCase());
        Theatre theatre = theatreService.getTheatre(
                showRequest.getTheatreName(),
                city);

        // Get Screen
        Screen screen = theatre.getScreens().stream()
                .filter(s -> s.getScreenId() == showRequest.getScreenId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        //  Create Show
        Show show = new Show(
                movie,
                screen,
                showRequest.getDate(),
                showRequest.getTime()
        );
        String showId = String.valueOf(idGenerator.incrementAndGet());
        show.setId(showId);
        showMap.put(showId, show);

        // Attach show to screen
        screen.addShow(show);

        return ShowResponse.builder()
                .theatreName(theatre.getName())
                .city(theatre.getCity().name())
                .shows(List.of(ShowResponse.ShowInfo.builder().showId(show.getId()).movieName(show.getMovie().getName()).startTime(show.getStartTime()).screenId(screen.getScreenId()).build()))
                .build();

    }

    @Override
    @CacheEvict(value = "shows", allEntries = true)
    public ShowResponse updateShow(String id, ShowRequest showRequest) {
        Show existingShow = Optional.ofNullable(showMap.get(id)).orElseThrow(() -> new RuntimeException("Show not Found"));
        Movie movie = movieService.getMovieByName(showRequest.getMovieName());
        City city = City.valueOf(showRequest.getCity().toUpperCase());
        Theatre theatre = theatreService.getTheatre(
                showRequest.getTheatreName(), city);
        Screen screen = theatre.getScreens().stream()
                .filter(s -> s.getScreenId() == showRequest.getScreenId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        removeShowFromAllScreens(existingShow);

        existingShow.setMovie(movie);
        existingShow.setShowDate(showRequest.getDate());
        existingShow.setStartTime(showRequest.getTime());
        screen.addShow(existingShow);

        return ShowResponse.builder()
                .theatreName(theatre.getName())
                .city(theatre.getCity().name())
                .shows(List.of(ShowResponse.ShowInfo.builder().showId(existingShow.getId()).movieName(existingShow.getMovie().getName()).startTime(existingShow.getStartTime()).screenId(screen.getScreenId()).build()))
                .build();
    }

    private void removeShowFromAllScreens(Show show) {
        for (City c : City.values()) {
            List<Theatre> theatres = theatreService.getTheatresByCity(c);

            for (Theatre t : theatres) {
                for (Screen screen : t.getScreens()) {
                    screen.removeShow(show);
                }
            }
        }
    }

    @Override
    @Cacheable(value = "shows", key = "#movie.name + '_' + #date + '_' + #theatre.name")
    public List<ShowResponse> getShows(Movie movie, LocalDate date, Theatre theatre) {

        List<ShowResponse.ShowInfo> showInfos = theatre.getScreens().stream()
                .flatMap(screen -> screen.getShows(date).stream()
                        .filter(show -> show.getMovie().getName().equals(movie.getName()))
                        .map(show -> ShowResponse.ShowInfo.builder()
                                .showId(show.getId())
                                .movieName(show.getMovie().getName())
                                .startTime(show.getStartTime())
                                .screenId(screen.getScreenId())
                                .build()
                        )
                ).toList();

        if (showInfos.isEmpty()) {
            return List.of();
        }

        ShowResponse response = ShowResponse.builder()
                .theatreName(theatre.getName())
                .city(theatre.getCity().name())
                .shows(showInfos)
                .build();

        return List.of(response);
    }

    @Override
    public List<ShowResponse> browseShowsTheaterWise(City city, Movie movie, LocalDate date) {

        List<Theatre> theatres = theatreService.getTheatresByCity(city);

        return theatres.stream()
                .map(theatre -> {

                    List<ShowResponse.ShowInfo> shows = theatre.getScreens().stream()
                            .flatMap(screen -> screen.getShows(date).stream())
                            .filter(show -> show.getMovie().getId().equals(movie.getId()))
                            .map(show -> ShowResponse.ShowInfo.builder()
                                    .showId(show.getId())
                                    .startTime(show.getStartTime())
                                    .movieName(show.getMovie().getName())
                                    .build()
                            )
                            .toList();

                    if (shows.isEmpty()) return null;

                    return ShowResponse.builder()
                            .theatreName(theatre.getName())
                            .city(theatre.getCity().name())
                            .shows(shows)
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Show getShowById(String showId) {
        return Optional.ofNullable(showMap.get(showId))
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));
    }

    @Override
    @CacheEvict(value = "shows", allEntries = true)
    public void deleteShow(String showId) {

        Show show = Optional.ofNullable(showMap.get(showId))
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));

        if (hasLockedSeats(show)) {
            throw new RuntimeException("Cannot delete show with locked seats");
        }

        show.getScreen().removeShow(show);

        showMap.remove(showId);
    }

    private boolean hasLockedSeats(Show show) {
        return show.getSeatStatusMap().values().stream()
                .anyMatch(status -> status == SeatStatus.SELECTED);
    }

}
