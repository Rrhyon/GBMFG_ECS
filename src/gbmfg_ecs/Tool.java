package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for Tool object creation.
 * Date: August 13, 2024
 */
public class Tool {

    private int toolId;
    private String name;
    private String description;
    private String condition;
    private boolean isAvailable;
    private String serialNum;
    private int categoryId;
    private int locationId;

    // Constructors
    public Tool() {
    }

    public Tool(String name, String description, String condition, 
            boolean isAvailable, String serialNum, int categoryId, 
            int locationId) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.isAvailable = isAvailable;
        this.serialNum = serialNum;
        this.categoryId = categoryId;
        this.locationId = locationId;
    }

    // Getters
    public int getToolId() {
        return toolId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getCondition() {
        return condition;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public String getSerialNum() {
        return serialNum;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public int getLocationId() {
        return locationId;
    }
    
    // Setters
    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
