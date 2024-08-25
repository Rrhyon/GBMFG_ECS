package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for category class object creation.
 * Date: August 13, 2024
 */
public class Category {

    private int categoryId;
    private String name;
    private String description;

    // Constructors
    public Category() {
    }

    // Constructor with all parameters
    public Category(int categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    // Constructor for use in updating (if no categoryId needed)
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters
    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    } 
    
    public String getDescription() {
        return description;
    }
        
    // Setters
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
