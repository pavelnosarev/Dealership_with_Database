package com.yearup.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private BasicDataSource basicDataSource;

    public VehicleDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String VIN = resultSet.getString("VIN");
                boolean sold = resultSet.getBoolean("SOLD");
                String color = resultSet.getString("color");
                String vehicleType = resultSet.getString("vehicle_type");

                Vehicle vehicle = new Vehicle(VIN, sold, color, vehicleType);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public void saveVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (VIN, SOLD, color, vehicle_type) VALUES (?, ?, ?, ?);";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, vehicle.getVIN());
            preparedStatement.setBoolean(2, vehicle.isSold());
            preparedStatement.setString(3, vehicle.getColor());
            preparedStatement.setString(4, vehicle.getVehicleType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET SOLD = ?, color = ?, vehicle_type = ? WHERE VIN = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setBoolean(1, vehicle.isSold());
            preparedStatement.setString(2, vehicle.getColor());
            preparedStatement.setString(3, vehicle.getVehicleType());
            preparedStatement.setString(4, vehicle.getVIN());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(Vehicle vehicle) {
        String query = "DELETE FROM vehicles WHERE VIN = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, vehicle.getVIN());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Vehicle getVehicleByVIN(String VIN) {
        Vehicle vehicle = null;

        String query = "SELECT * FROM vehicles WHERE VIN = ?;";

        try (Connection connection = this.basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, VIN);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean sold = resultSet.getBoolean("SOLD");
                    String color = resultSet.getString("color");
                    String vehicleType = resultSet.getString("vehicle_type");

                    vehicle = new Vehicle(VIN, sold, color, vehicleType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

}
