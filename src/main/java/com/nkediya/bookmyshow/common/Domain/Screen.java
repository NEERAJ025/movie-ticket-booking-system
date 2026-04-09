package com.nkediya.bookmyshow.common.Domain;

import com.nkediya.bookmyshow.show.domain.Show;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Screen {
    private final int screenId;
    private final List<Seat> seats;
    //price
    private final Map<LocalDate, List<Show>> showsByDate = new HashMap<>();

    public Screen(int screenId, List<Seat> seats) {
        this.screenId = screenId;
        this.seats = seats;
    }

    public void addShow(Show show) {
        showsByDate.computeIfAbsent(show.getShowDate(), d -> new ArrayList<>()).add(show);
    }

    public void removeShow(Show show) {
        List<Show> shows = showsByDate.get(show.getShowDate());

        if (shows != null) {
            shows.removeIf(s -> s.getId().equals(show.getId()));

            // Optional cleanup (VERY GOOD PRACTICE)
            if (shows.isEmpty()) {
                showsByDate.remove(show.getShowDate());
            }
        }
    }

    public List<Show> getShows(LocalDate date) {
        return showsByDate.getOrDefault(date, List.of());
    }

    public List<Show> getAllShows() {
        return showsByDate.values()
                .stream()
                .flatMap(List::stream)
                .toList();
    }

}
