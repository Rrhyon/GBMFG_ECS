package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for location object creation.
 * Date: August 13, 2024
 */
public class Location {

    private int locationId;
    private String name;
    private String description;

    // Constructors
    public Location() {
        
    }

    public Location(int locationId, String name, String description) {
        this.locationId = locationId;
        this.name = name;
        this.description = description;
    }

    // Getters
    public int getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
