package com.travel.booking.dto;

public class FareRequest {
    private Double distance;
    private String cabType;

    public FareRequest() {}

    public FareRequest(Double distance, String cabType) {
        this.distance = distance;
        this.cabType = cabType;
    }

    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public String getCabType() { return cabType; }
    public void setCabType(String cabType) { this.cabType = cabType; }
}