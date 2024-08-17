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
    public String addCategory(int categoryId, String name, String description) {
        Category category = new Category(categoryId, name, description);
        return categoryDAO.addCategory(category);
    }
    
    // Update category information
    public String updateCategory(int categoryId, String name, 
            String description) {
        Category category = new Category(categoryId, name, description);
        category.setCategoryId(categoryId);
        return categoryDAO.updateCategory(category);
    }
    
    // Retrieves specific category by ID
    public Category getCategory(int categoryId) {
        return categoryDAO.getCategory(categoryId);
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
