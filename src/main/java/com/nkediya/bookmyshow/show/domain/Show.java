package com.nkediya.bookmyshow.show.domain;

import com.nkediya.bookmyshow.common.Domain.Screen;
import com.nkediya.bookmyshow.common.Domain.Seat;
import com.nkediya.bookmyshow.common.enums.SeatStatus;
import com.nkediya.bookmyshow.movie.dto.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Show {
    private String id;
    private Movie movie;
    private LocalDate showDate;
    private LocalTime startTime;
    private final Screen screen;
    private final Map<Integer, SeatStatus> seatStatusMap = new HashMap<>();
    private final Map<Integer, ReentrantLock> seatLocks = new HashMap<>();
    private final Map<Integer, Long> seatLockTime = new HashMap<>();
    private static final long LOCK_TIMEOUT = 2 * 60 * 1000; // 2 minutes

    public Show(Movie movie, Screen screen, LocalDate date, LocalTime time) {
        this.movie = movie;
        this.showDate = date;
        this.startTime = time;
        this.screen = screen;

        for (Seat seat : screen.getSeats()) {
            seatStatusMap.put(seat.getSeatId(), SeatStatus.AVAILABLE);
            seatLocks.put(seat.getSeatId(), new ReentrantLock());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Screen getScreen() {
        return screen;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean lockSeats(List<Integer> seatIds) {
        List<Integer> sorted = new ArrayList<>(seatIds);

        //sorting i am doing to avoid deadlock scenario
        Collections.sort(sorted);

        List<ReentrantLock> acquiredLocks = new ArrayList<>();

        try {
            // Phase 1: acquire all locks
            for (int seatId : sorted) {
                ReentrantLock lock = seatLocks.get(seatId);
                boolean isLocked = false;
                try {
                    isLocked = lock.tryLock(10, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }

                if (!isLocked) {
                    return false;
                }
                acquiredLocks.add(lock);
            }

            // Phase 2: validate availability
            for (int seatId : sorted) {
                if (seatStatusMap.get(seatId) != SeatStatus.AVAILABLE) {
                    return false;
                }
            }

            // Phase 3: mark LOCKED
            for (int seatId : sorted) {
                seatStatusMap.put(seatId, SeatStatus.SELECTED);
                seatLockTime.put(seatId, System.currentTimeMillis());
            }

            return true;

        } finally {
            // Phase 4: release locks
            for (ReentrantLock lock : acquiredLocks) {
                lock.unlock();
            }
        }
    }

    public void confirmSeats(List<Integer> seatIds) {
        for (int seatId : seatIds) {
            seatStatusMap.put(seatId, SeatStatus.BOOKED);
        }
    }

    public void releaseSeats(List<Integer> seatIds) {
        for (int seatId : seatIds) {
            seatStatusMap.put(seatId, SeatStatus.AVAILABLE);
        }
    }

    public void releaseExpiredSeats() {
        long now = System.currentTimeMillis();

        for (Integer seatId : seatLockTime.keySet()) {
            if (seatStatusMap.get(seatId) == SeatStatus.SELECTED &&
                    now - seatLockTime.get(seatId) > LOCK_TIMEOUT) {

                seatStatusMap.put(seatId, SeatStatus.AVAILABLE);
            }
        }
    }



    public Map<Integer, SeatStatus> getSeatStatusMap() {
        return seatStatusMap;
    }


}
