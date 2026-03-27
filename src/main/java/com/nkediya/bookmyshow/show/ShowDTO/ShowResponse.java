package com.nkediya.bookmyshow.show.ShowDTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class ShowResponse {
    private String theatreName;
    private String city;

    private List<ShowInfo> shows;

    @Data
    @Builder
    public static class ShowInfo {
        private String showId;
        private LocalTime startTime;
        private String movieName;
    }
}
