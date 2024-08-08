package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public String addMaterial(Material material) {
        String sql = "INSERT INTO materials (name, description, quantity, unit) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getName());
            stmt.setString(2, material.getDescription());
            stmt.setDouble(3, material.getQuantity());
            stmt.setString(4, material.getUnit());
            stmt.executeUpdate();
            return "Material added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding material.";
        }
    }

    public String removeMaterial(int materialId) {
        String sql = "DELETE FROM materials WHERE materialId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public Material getMaterial(int materialId) {
        String sql = "SELECT * FROM materials WHERE materialId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Material material = new Material(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("quantity"),
                        rs.getString("unit")
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

    public String updateMaterial(Material material) {
        String sql = "UPDATE materials SET name = ?, description = ?, quantity = ?, unit = ? WHERE materialId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getName());
            stmt.setString(2, material.getDescription());
            stmt.setDouble(3, material.getQuantity());
            stmt.setString(4, material.getUnit());
            stmt.setInt(5, material.getMaterialId());
            stmt.executeUpdate();
            return "Material updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating material.";
        }
    }

    public List<Material> getAllMaterials() {
        String sql = "SELECT * FROM materials";
        List<Material> materials = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Material material = new Material(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("quantity"),
                        rs.getString("unit")
                );
                material.setMaterialId(rs.getInt("materialId"));
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }
}
