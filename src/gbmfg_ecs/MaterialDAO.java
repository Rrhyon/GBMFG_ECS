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

public class MaterialDAO {

    public String addMaterial(Material material) {
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

    public String updateMaterial(Material material) {
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
            stmt.setInt(7, material.getMaterialId()); // Added this line
            stmt.executeUpdate();
            return "Material updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating material.";
        }
    }

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
            e.printStackTrace();
            return null;
        }
    }

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
            e.printStackTrace();
        }
        return materials;
    }

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
            e.printStackTrace();
            return "Error removing material.";
        }
    }
}

