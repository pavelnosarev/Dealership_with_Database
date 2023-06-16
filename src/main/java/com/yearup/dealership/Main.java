package com.yearup.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private BasicDataSource basicDataSource;

    public static void main(String[] args) {
        BasicDataSource basicDataSource = new BasicDataSource();

        String username = args[0];
        String password = args[1];

        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dealership?reconnect=true");
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        DealershipDAO dealershipDAO = new DealershipDAO(basicDataSource);
        VehicleDAO vehicleDAO = new VehicleDAO(basicDataSource);

        // Get all dealerships
        List<Dealership> dealerships = dealershipDAO.getAllDealerships();
        System.out.println("All dealerships:");
        for (Dealership dealership : dealerships) {
            System.out.println(dealership);
        }
        System.out.println();

        // Get vehicles by dealership
        int dealershipId = 1;
        List<Vehicle> vehiclesByDealership = dealershipDAO.getVehiclesByDealership(dealershipId);
        System.out.println("Vehicles for Dealership (ID: " + dealershipId + "):");
        for (Vehicle vehicle : vehiclesByDealership) {
            System.out.println(vehicle);
        }
        System.out.println();

        // Get vehicle by VIN
        String VIN = "VIN123";
        Vehicle vehicleByVIN = vehicleDAO.getVehicleByVIN(VIN);
        if (vehicleByVIN != null) {
            System.out.println("Vehicle with VIN " + VIN + ":");
            System.out.println(vehicleByVIN);
        } else {
            System.out.println("No vehicle found with VIN " + VIN);
        }
        System.out.println();

        // Get vehicle location by VIN
        String vehicleLocation = dealershipDAO.getVehicleLocationByVIN(VIN);
        if (vehicleLocation != null) {
            System.out.println("Location of Vehicle (VIN: " + VIN + "): " + vehicleLocation);
        } else {
            System.out.println("No vehicle found with VIN " + VIN);
        }
        System.out.println();

        // Get vehicles by dealership and sale date range
        String startDate = "2023-01-01";
        String endDate = "2023-06-30";
        List<Vehicle> vehiclesByDealershipAndSaleDateRange = dealershipDAO.getVehiclesByDealershipAndSaleDateRange(dealershipId, startDate, endDate);
        System.out.println("Vehicles for Dealership (ID: " + dealershipId + ") within " + startDate + " and " + endDate + ":");
        for (Vehicle vehicle : vehiclesByDealershipAndSaleDateRange) {
            System.out.println(vehicle);
        }
    }


}