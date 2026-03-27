package com.nkediya.test.bookmyshow.theatre.TheatreService;

import com.nkediya.test.bookmyshow.common.Domain.Screen;
import com.nkediya.test.bookmyshow.common.Domain.Seat;
import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import com.nkediya.test.bookmyshow.common.enums.City;
import com.nkediya.test.bookmyshow.common.enums.SeatCategory;
import com.nkediya.test.bookmyshow.movie.MovieDTO.Movie;
import com.nkediya.test.bookmyshow.theatre.TheatreDTO.TheatreRequest;
import com.nkediya.test.bookmyshow.theatre.TheatreDTO.TheatreResponse;
import com.nkediya.test.bookmyshow.theatre.TheatreDomain.Theatre;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final Map<City, List<Theatre>> cityTheatres = new HashMap<>();

    @Override
    public Theatre addTheatre(TheatreRequest theatreReq) {

        List<Screen> screens = theatreReq.getScreens().stream().map(s -> new Screen(s.getScreenId(), createSeats(s.getTotalSeats()))).toList();

        Theatre theatre = new Theatre(theatreReq.getName(), City.valueOf(theatreReq.getCity().toUpperCase()), screens);

        addTheatreToMap(theatre);

        return theatre;
    }

    private void addTheatreToMap(Theatre theatre) {
        cityTheatres.computeIfAbsent(theatre.getCity(), c -> new ArrayList<>()).add(theatre);
    }

    @Override
    public Set<Movie> getMovies(City city, LocalDate date) {
        Set<Movie> movies = new HashSet<>();
        List<Theatre> theatres = cityTheatres.getOrDefault(city, List.of());

        for (Theatre theatre : theatres) {
            for (Screen screen : theatre.getScreens()) {
                for (Show show : screen.getShows(date)) {
                    movies.add(show.getMovie());
                }
            }
        }
        return movies;
    }

    @Override
    public List<TheatreResponse> getTheatres(City city, Movie movie, LocalDate date) {
        List<Theatre> theatres = cityTheatres.getOrDefault(city, List.of());
        return theatres.stream().filter(theatre -> theatre.getScreens()
                .stream()
                .anyMatch(screen -> screen.getShows(date)
                        .stream()
                        .anyMatch(show -> show.getMovie().getName().equals(movie.getName()))))
                .map(theatre -> TheatreResponse.builder().name(theatre.getName()).city(theatre.getCity().name())
                        .build()).toList();
    }

    public Theatre getTheatre(String theatreName, City city) {
        return cityTheatres.getOrDefault(city, List.of()).stream().filter(t -> t.getName().equalsIgnoreCase(theatreName)).findFirst().orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    @Override
    public List<Theatre> getTheatresByCity(City city) {
        return cityTheatres.getOrDefault(city, List.of());
    }

    private List<Seat> createSeats(int totalSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i, SeatCategory.SILVER));
        }
        return seats;
    }


}
