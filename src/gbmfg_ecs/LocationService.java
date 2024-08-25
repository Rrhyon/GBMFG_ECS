package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class LocationService {

    private LocationDAO locationDAO;

    public LocationService() {
        this.locationDAO = new LocationDAO();
    }

    // Adds a new unique location for tools or materials
    public String saveLocation(Location location) {
        return locationDAO.saveLocation(location);
    }
    
    // Update location information
    public String saveLocationUpdates(Location location) {
        return locationDAO.saveLocationUpdates(location);
    }

    // Retrieves specific location by ID
    public Location getLocationById(int locationId) {
        return locationDAO.getLocationById(locationId);
    }
    
    // Retrieves specific location by Name
    public Location getLocationByName(String locationName) {
        return locationDAO.getLocationByName(locationName);
    }

    // Creates a list and retrieves all available locations
    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }
    
    // Removes selected locations
    public String removeLocation(int locationId) {
        return locationDAO.removeLocation(locationId);
    }
}
