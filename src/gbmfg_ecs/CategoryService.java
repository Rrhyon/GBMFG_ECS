package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    // Adds a new unique category for tools or materials
    public String saveCategory(Category category) {
        return categoryDAO.saveCategory(category);
    }
    
    // Update category information
    public String saveCategoryUpdates(Category category) {
        return categoryDAO.saveCategoryUpdates(category);
    }
    
    // Retrieves specific category by ID
    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }
    
    // Retrieves specific category by Name
    public Category getCategoryByName(String catName) {
        return categoryDAO.getCategoryByName(catName);
    }

    // Creates a list and retrieves all available categories
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
    
    // Removes selected categories
    public String removeCategory(int categoryId) {
        return categoryDAO.removeCategory(categoryId);
    }
}
