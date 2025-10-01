package com.travel.booking.service;

import com.travel.booking.dto.FareRequest;
import com.travel.booking.dto.FareResponse;
import com.travel.booking.model.Booking;
import com.travel.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String FARE_SERVICE_URL = "http://localhost:8081/api/fare/calculate";

    public Booking createBooking(Booking booking) {
        // Calculate fare from fare service
        FareRequest fareRequest = new FareRequest(booking.getDistance(), booking.getCabType());

        try {
            FareResponse fareResponse = restTemplate.postForObject(
                FARE_SERVICE_URL,
                fareRequest,
                FareResponse.class
            );

            if (fareResponse != null) {
                booking.setFare(fareResponse.getFare());
            }
        } catch (Exception e) {
            // Fallback fare calculation if service is down
            booking.setFare(calculateFallbackFare(booking.getDistance(), booking.getCabType()));
        }

        booking.setBookingStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByPassenger(String passengerName) {
        return bookingRepository.findByPassengerName(passengerName);
    }

    public List<Booking> getBookingsByPhone(String phoneNumber) {
        return bookingRepository.findByPhoneNumber(phoneNumber);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setPassengerName(bookingDetails.getPassengerName());
        booking.setPhoneNumber(bookingDetails.getPhoneNumber());
        booking.setEmail(bookingDetails.getEmail());
        booking.setPickupLocation(bookingDetails.getPickupLocation());
        booking.setDropLocation(bookingDetails.getDropLocation());
        booking.setDistance(bookingDetails.getDistance());
        booking.setCabType(bookingDetails.getCabType());

        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setBookingStatus("CANCELLED");
        return bookingRepository.save(booking);
    }

    private Double calculateFallbackFare(Double distance, String cabType) {
        double baseRate = switch (cabType != null ? cabType.toUpperCase() : "ECONOMY") {
            case "PREMIUM" -> 15.0;
            case "LUXURY" -> 20.0;
            default -> 10.0;
        };
        return distance * baseRate;
    }
}