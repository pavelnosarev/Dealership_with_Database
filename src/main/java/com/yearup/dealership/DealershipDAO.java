package com.yearup.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private BasicDataSource basicDataSource;

    public DealershipDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public List<Dealership> getAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();

        String query = "SELECT * FROM dealerships;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                Dealership dealership = new Dealership(id, name, address, phone);
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dealerships;
    }

    public void saveDealership(Dealership dealership) {
        String query = "INSERT INTO dealerships (name, address, phone) VALUES (?, ?, ?);";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDealership(Dealership dealership) {
        String query = "UPDATE dealerships SET name = ?, address = ?, phone = ? WHERE id = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());
            preparedStatement.setInt(4, dealership.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDealership(Dealership dealership) {
        String query = "DELETE FROM dealerships WHERE id = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, dealership.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Vehicle> getVehiclesByDealership(int dealershipId) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE dealership_id = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, dealershipId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String VIN = resultSet.getString("VIN");
                    boolean sold = resultSet.getBoolean("SOLD");
                    String color = resultSet.getString("color");
                    String vehicleType = resultSet.getString("vehicle_type");

                    Vehicle vehicle = new Vehicle(VIN, sold, color, vehicleType);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public String getVehicleLocationByVIN(String VIN) {
        String location = null;

        String query = "SELECT d.address FROM dealerships d INNER JOIN vehicles v ON d.id = v.dealership_id WHERE v.VIN = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, VIN);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    location = resultSet.getString("address");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;
    }

    public List<Vehicle> getVehiclesByDealershipAndSaleDateRange(int dealershipId, String startDate, String endDate) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE dealership_id = ? AND sale_date >= ? AND sale_date <= ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String VIN = resultSet.getString("VIN");
                    boolean sold = resultSet.getBoolean("SOLD");
                    String color = resultSet.getString("color");
                    String vehicleType = resultSet.getString("vehicle_type");

                    Vehicle vehicle = new Vehicle(VIN, sold, color, vehicleType);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }


}
