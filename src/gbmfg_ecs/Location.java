package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public class Location {

    private int locationId;
    private String name;
    private String description;

    // Constructors, getters, and setters
    public Location() {
    }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
