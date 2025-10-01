package com.travel.booking.dto;

public class FareResponse {
    private Double fare;
    private String cabType;
    private Double distance;
    
    public FareResponse() {}
    
    public FareResponse(Double fare, String cabType, Double distance) {
        this.fare = fare;
        this.cabType = cabType;
        this.distance = distance;
    }
    
    public Double getFare() { return fare; }
    public void setFare(Double fare) { this.fare = fare; }
    
    public String getCabType() { return cabType; }
    public void setCabType(String cabType) { this.cabType = cabType; }
    
    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }
}