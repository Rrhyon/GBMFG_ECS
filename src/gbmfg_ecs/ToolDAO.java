package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToolDAO {

    public String addTool(Tool tool) {
        String sql = "INSERT INTO tool (toolName, toolDesc, toolCondition, "
                + "isAvailable, toolSerial, categoryId, locationId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tool.getName());
            stmt.setString(2, tool.getDescription());
            stmt.setString(3, tool.getCondition());
            stmt.setBoolean(4, tool.isAvailable());
            stmt.setString(5, tool.getSerialNum());
            stmt.setInt(6, tool.getCategoryId());
            stmt.setInt(7, tool.getLocationId());
            stmt.executeUpdate();
            return "Tool added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding tool.";
        }
    }

    public String removeTool(int toolId) {
        String sql = "DELETE FROM tool WHERE toolId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, toolId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Tool removed successfully.";
            } else {
                return "Tool not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing tool.";
        }
    }

    public Tool getTool(int toolId) {
        String sql = "SELECT * FROM tool WHERE toolId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, toolId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tool tool = new Tool(
                        rs.getString("toolName"),
                        rs.getString("toolDesc"),
                        rs.getString("toolCondition"),
                        rs.getBoolean("isAvailable"),
                        rs.getString("toolSerial"),
                        rs.getInt("categoryId"),
                        rs.getInt("locationId")
                );
                tool.setToolId(rs.getInt("toolId"));
                return tool;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateTool(Tool tool) {
        String sql = "UPDATE tool SET toolName = ?, toolDesc = ?, "
                + "toolCondition = ?, isAvailable = ?, toolSerial = ?, "
                + "categoryId = ?, locationId = ? WHERE toolId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tool.getName());
            stmt.setString(2, tool.getDescription());
            stmt.setString(3, tool.getCondition());
            stmt.setBoolean(4, tool.isAvailable());
            stmt.setString(5, tool.getSerialNum());
            stmt.setInt(6, tool.getCategoryId());
            stmt.setInt(7, tool.getLocationId());
            stmt.setInt(8, tool.getToolId());
            stmt.executeUpdate();
            return "Tool updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating tool.";
        }
    }

    public List<Tool> getAllTools() {
        String sql = "SELECT * FROM tool";
        List<Tool> tools = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tool tool = new Tool(
                        rs.getString("toolName"),
                        rs.getString("toolDesc"),
                        rs.getString("toolCondition"),
                        rs.getBoolean("isAvailable"),
                        rs.getString("toolSerial"),
                        rs.getInt("categoryId"),
                        rs.getInt("locationId")
                );
                tool.setToolId(rs.getInt("toolId"));
                tools.add(tool);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tools;
    }
}
