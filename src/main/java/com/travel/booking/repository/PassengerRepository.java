package com.travel.booking.repository;

import com.travel.booking.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByPhoneNumber(String phoneNumber);
    Optional<Passenger> findByEmail(String email);
}