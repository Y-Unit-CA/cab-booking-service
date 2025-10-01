package com.travel.booking.service;

import com.travel.booking.model.Passenger;
import com.travel.booking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public Optional<Passenger> getPassengerByPhone(String phoneNumber) {
        return passengerRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }

    public Passenger updatePassenger(Long id, Passenger passengerDetails) {
        Passenger passenger = passengerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));

        passenger.setName(passengerDetails.getName());
        passenger.setPhoneNumber(passengerDetails.getPhoneNumber());
        passenger.setEmail(passengerDetails.getEmail());
        passenger.setAddress(passengerDetails.getAddress());

        return passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}