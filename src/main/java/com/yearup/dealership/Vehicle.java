package com.yearup.dealership;

public class Vehicle {
    private String VIN;
    private boolean sold;
    private String color;
    private String vehicleType;
    private String dealershipName;

    public Vehicle(String VIN, boolean sold, String color, String vehicleType) {
        this.VIN = VIN;
        this.sold = sold;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public void setDealershipName(String dealershipName) {
        this.dealershipName = dealershipName;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "VIN='" + VIN + '\'' +
                ", sold=" + sold +
                ", color='" + color + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", dealershipName='" + dealershipName + '\'' +
                '}';
    }
}

