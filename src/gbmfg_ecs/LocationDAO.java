package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    public String addLocation(Location location) {
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
            e.printStackTrace();
            return "Error adding location.";
        }
    }

    public String removeLocation(int locationId) {
        String sql = "DELETE FROM location WHERE locationId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locationId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Location removed successfully.";
            } else {
                return "Location not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing location.";
        }
    }

    public Location getLocation(int locationId) {
        String sql = "SELECT * FROM location WHERE locationId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Location location = new Location(
                        rs.getInt(locationId),
                        rs.getString("locationName"),
                        rs.getString("locationDesc")
                );
                location.setLocationId(rs.getInt("locationId"));
                return location;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateLocation(Location location) {
        String sql = "UPDATE location SET locationId = ?, locationName = ?, locationDesc = ? WHERE locationId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, location.getName());
            stmt.setString(2, location.getDescription());
            stmt.setInt(3, location.getLocationId());
            stmt.executeUpdate();
            return "Location updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating location.";
        }
    }

    public List<Location> getAllLocations() {
        String sql = "SELECT * FROM location";
        List<Location> locations = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            e.printStackTrace();
        }
        return locations;
    }
}
