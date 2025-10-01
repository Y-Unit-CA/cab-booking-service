package com.travel.booking.repository;

import com.travel.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPassengerName(String passengerName);
    List<Booking> findByPhoneNumber(String phoneNumber);
    List<Booking> findByBookingStatus(String status);
}