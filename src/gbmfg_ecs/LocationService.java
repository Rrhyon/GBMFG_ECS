package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.util.List;

public class LocationService {

    private LocationDAO locationDAO;

    public LocationService() {
        this.locationDAO = new LocationDAO();
    }

    public String addLocation(String name, String description) {
        Location location = new Location(name, description);
        return locationDAO.addLocation(location);
    }

    public String removeLocation(int locationId) {
        return locationDAO.removeLocation(locationId);
    }

    public Location getLocation(int locationId) {
        return locationDAO.getLocation(locationId);
    }

    public String updateLocation(int locationId, String name, String description) {
        Location location = new Location(name, description);
        location.setLocationId(locationId);
        return locationDAO.updateLocation(location);
    }

    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }
}
