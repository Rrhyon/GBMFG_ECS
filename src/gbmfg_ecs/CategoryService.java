package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public String addCategory(int categoryId, String name, String description) {
        Category category = new Category(categoryId, name, description);
        return categoryDAO.addCategory(category);
    }

    public String removeCategory(int categoryId) {
        return categoryDAO.removeCategory(categoryId);
    }

    public Category getCategory(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    public String updateCategory(int categoryId, String name, String description) {
        Category category = new Category(categoryId, name, description);
        category.setCategoryId(categoryId);
        return categoryDAO.updateCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
