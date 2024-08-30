package gbmfg_ecs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for Location class.
 * Date: August 13, 2024
 */
public class LocationDAO {

    /* Method to create SQL prepared statement to create a location record
     * after entering location information.
     */
    public String saveLocation(Location location) {
        String sql = "INSERT INTO location (locationId, locationName, "
                + "locationDesc) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, location.getLocationId());
            stmt.setString(2, location.getName());
            stmt.setString(3, location.getDescription());
            stmt.executeUpdate();
            return "Location added successfully.";
        } catch (SQLException e) {
            return "Error adding location.";
        }
    }
    
    /* Method to create SQL prepared statement to update a location record
     * after entering location information.
     */
    public String saveLocationUpdates(Location location) {
        String sql = "UPDATE location SET locationName = ?, "
                + "locationDesc = ? WHERE locationId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location.getName());
            stmt.setString(2, location.getDescription());
            stmt.setInt(3, location.getLocationId());
            stmt.executeUpdate();
            return "Location updated successfully.";
        } catch (SQLException e) {
            return "Error updating location.";
        }
    }

    /* Method to create SQL prepared statement to retrieve a location record
     * after entering location information.
     */
    public Location getLocationById(int locationId) {
        String sql = "SELECT * FROM location WHERE locationId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Location location = new Location(
                        locationId,
                        rs.getString("locationName"),
                        rs.getString("locationDesc")
                );
                return location;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
    
    /* Method to create SQL prepared statement to retrieve a location record
     * after entering location information.
     */
    public Location getLocationByName(String locationName) {
        String sql = "SELECT * FROM location WHERE locationName = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, locationName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Location location = new Location(
                        rs.getInt("locationId"),
                        locationName,
                        rs.getString("locationDesc")
                );
                return location;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    /* Method to create SQL prepared statement to create a new ArrayList called
     * 'locations' and add all locations to the array.
     */
    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM location";
        List<Location> locations = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Location location = new Location(
                        rs.getInt("locationId"),
                        rs.getString("locationName"),
                        rs.getString("locationDesc")
                );
                location.setLocationId(rs.getInt("locationId"));
                locations.add(location);
            }
        } catch (SQLException e) {
        }
        return locations;
    }
    
    /* Method to SQL prepared statement to remove a location record
     * after entering location information.
     */
    public String removeLocation(int locationId) {
        String sql = "DELETE FROM location WHERE locationId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locationId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Location removed successfully.";
            } else {
                return "Location not found.";
            }
        } catch (SQLException e) {
            return "Error removing location.";
        }
    }
}
