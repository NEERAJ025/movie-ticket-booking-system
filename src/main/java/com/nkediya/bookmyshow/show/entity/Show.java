package com.nkediya.bookmyshow.show.entity;

import com.nkediya.bookmyshow.common.enums.SeatStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Show {

    @Id
    private String id;

    private String movieName;
    private LocalDate showDate;
    private LocalTime startTime;

    @Version
    private Integer version;

    @ElementCollection
    @CollectionTable(name = "show_seats")
    @MapKeyColumn(name = "seat_number")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Map<Integer, SeatStatus> seatStatusMap = new HashMap<>();
}