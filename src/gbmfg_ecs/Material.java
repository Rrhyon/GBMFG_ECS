package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for Material object creation.
 * Date: August 13, 2024
 */
public class Material {

    private int materialId;
    private String name;
    private String description;
    private double quantity;
    private String unit;
    private int categoryId;
    private int locationId;

    // Default Constructor
    public Material() {
    }

    // Constructor without materialId (for new materials)
    public Material(String name, String description, double quantity, 
                    String unit, int categoryId, int locationId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.categoryId = categoryId;
        this.locationId = locationId;
    }

    // Constructor with materialId (for existing materials)
    public Material(int materialId, String name, String description, double quantity, 
                    String unit, int categoryId, int locationId) {
        this.materialId = materialId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.categoryId = categoryId;
        this.locationId = locationId;
    }

    // Getters
    public int getMaterialId() {
        return materialId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getLocationId() {
        return locationId;
    }

    // Setters
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
