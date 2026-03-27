package com.nkediya.bookmyshow.show.service;

import com.nkediya.bookmyshow.common.Domain.Screen;
import com.nkediya.bookmyshow.common.enums.City;
import com.nkediya.bookmyshow.movie.dto.Movie;
import com.nkediya.bookmyshow.movie.service.MovieService;
import com.nkediya.bookmyshow.show.dto.ShowRequest;
import com.nkediya.bookmyshow.show.dto.ShowResponse;
import com.nkediya.bookmyshow.show.domain.Show;
import com.nkediya.bookmyshow.theatre.domain.Theatre;
import com.nkediya.bookmyshow.theatre.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final Map<String, Show> showMap= new HashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger();

    @Override
    public Show createShow(ShowRequest showRequest) {
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

        return show;
    }

    @Override
    public List<Show> getShows(Movie movie, LocalDate date, Theatre theatre) {
        return theatre.getScreens().stream()
                .flatMap(screen -> screen.getShows(date).stream())
                .filter(show -> show.getMovie().getName().equals(movie.getName())).toList();
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
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + showId));
    }

}
