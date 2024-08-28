package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for Material class.
 * Date: August 13, 2024
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {

    // Method to create SQL prepared statement to create a material
    public String saveMaterial(Material material) {
        String sql = "INSERT INTO material (matName, matDesc, matQuantity, "
                + "matUnit, categoryId, locationId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getName());
            stmt.setString(2, material.getDescription());
            stmt.setDouble(3, material.getQuantity());
            stmt.setString(4, material.getUnit());
            stmt.setInt(5, material.getCategoryId());
            stmt.setInt(6, material.getLocationId());
            stmt.executeUpdate();
            return "Material added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding material.";
        }
    }

    // Method to create SQL prepared statement to update materials
    public String saveMaterialUpdates(Material material) {
        String sql = "UPDATE material SET matName = ?, matDesc = ?, "
                + "matQuantity = ?, matUnit = ?, categoryId = ?, "
                + "locationId = ? WHERE materialId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getName());
            stmt.setString(2, material.getDescription());
            stmt.setDouble(3, material.getQuantity());
            stmt.setString(4, material.getUnit());
            stmt.setInt(5, material.getCategoryId());
            stmt.setInt(6, material.getLocationId());
            stmt.setInt(7, material.getMaterialId());
            stmt.executeUpdate();
                return "Material updated successfully.";
        } catch (SQLException e) {
            return "Error updating material.";
        }
    }

    // Method to create SQL prepared statement to retrieve material by ID
    public Material getMaterial(int materialId) {
        String sql = "SELECT * FROM material WHERE materialId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Material material = new Material(
                    rs.getString("matName"),
                    rs.getString("matDesc"),
                    rs.getDouble("matQuantity"),
                    rs.getString("matUnit"),
                    rs.getInt("categoryId"),
                    rs.getInt("locationId")
                );
                material.setMaterialId(rs.getInt("materialId"));
                return material;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    // Method to create SQL prepared statement to retrieve all materials
    public List<Material> getAllMaterials() {
        String sql = "SELECT * FROM material";
        List<Material> materials = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Material material = new Material(
                    rs.getString("matName"),
                    rs.getString("matDesc"),
                    rs.getDouble("matQuantity"),
                    rs.getString("matUnit"),
                    rs.getInt("categoryId"),
                    rs.getInt("locationId")
                );
                material.setMaterialId(rs.getInt("materialId"));
                materials.add(material);
            }
        } catch (SQLException e) {
        }
        return materials;
    }
    
    // Method to create SQL prepared statement to search for materials by name or description
    public List<Material> searchMaterials(String inquiry) {
        String sql = "SELECT * FROM material WHERE matName LIKE ? OR matDesc LIKE ?";
        List<Material> materials = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            String query = "%" + inquiry + "%";
            stmt.setString(1, query);
            stmt.setString(2, query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Material material = new Material(
                    rs.getString("matName"),
                    rs.getString("matDesc"),
                    rs.getDouble("matQuantity"),
                    rs.getString("matUnit"),
                    rs.getInt("categoryId"),
                    rs.getInt("locationId")
                );
                material.setMaterialId(rs.getInt("materialId"));
                materials.add(material);
            }
        } catch (SQLException e) {
        }
        return materials;
    }
    
    // Method to create SQL prepared statement to remove a material record by ID
    public String removeMaterial(int materialId) {
        String sql = "DELETE FROM material WHERE materialId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Material removed successfully.";
            } else {
                return "Material not found.";
            }
        } catch (SQLException e) {
            return "Error removing material.";
        }
    }
}

