package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public String addCategory(Category category) {
        String sql = "INSERT INTO category (categoryId, catName, "
                + "catDesc) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category.getCategoryId());
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getDescription());
            stmt.executeUpdate();
            return "Category added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding category.";
        }
    }

    public String removeCategory(int categoryId) {
        String sql = "DELETE FROM category WHERE categoryId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Category removed successfully.";
            } else {
                return "Category not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing category.";
        }
    }

    public Category getCategory(int categoryId) {
        String sql = "SELECT * FROM category WHERE categoryId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Category category = new Category(
                        rs.getInt(categoryId),
                        rs.getString("catName"),
                        rs.getString("catDesc")
                );
                category.setCategoryId(rs.getInt("categoryId"));
                return category;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateCategory(Category category) {
        String sql = "UPDATE category SET categoryId = ?, catName = ?, "
                + "catDesc = ? WHERE categoryId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDescription());
            stmt.setInt(3, category.getCategoryId());
            stmt.executeUpdate();
            return "Category updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating category.";
        }
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("categoryId"),
                        rs.getString("catName"),
                        rs.getString("catDesc")
                );
                category.setCategoryId(rs.getInt("categoryId"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
