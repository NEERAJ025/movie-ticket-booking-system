package com.nkediya.bookmyshow.show.repository;


import com.nkediya.bookmyshow.show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {

    List<Show> findByMovieNameAndShowDate(String movieName, LocalDate date);
}