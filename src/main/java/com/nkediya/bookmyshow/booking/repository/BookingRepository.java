package com.nkediya.bookmyshow.booking.repository;

import com.nkediya.bookmyshow.booking.entity.Booking;

import com.nkediya.bookmyshow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUser(User user);
}
