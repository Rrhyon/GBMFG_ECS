package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for Tool class.
 * Date: August 13, 2024
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToolDAOImpl implements ToolDAO {

    /* Method to create SQL prepared statement to create a tool after entering
     * tool information.
     */
    @Override
    public String saveTool(Tool tool) {
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
            return "Error adding tool.";
        }
    }

    /* Method to create SQL prepared statement to update tool after entering
     * new tool information.
     */
    @Override
    public String saveToolUpdates(Tool tool) {
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
            return "Error updating tool.";
        }
    }
       
    /* Method to create SQL prepared statement to retrieve tool after entering 
     * tool ID.
     */
    @Override
    public Tool getTool(int toolId) {
        String sql = "SELECT * FROM tool WHERE toolId = ? ";
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
            return null;
        }
    }

    /* Method to create SQL prepared statement to create a new ArrayList called
     * 'tools' and add all tools to the array.
     */
    @Override
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
        }
        return tools;
    }
    
    /* Method to create SQL prepared statement to search for a tool 
     * after entering tool information.
     */
    @Override
    public List<Tool> searchTools(String inquiry) {
        String sql = "SELECT * FROM tool WHERE toolName LIKE ? OR "
                + "toolDesc LIKE ? OR "
                + "toolCondition LIKE ? OR "
                + "toolSerial LIKE ?";
        List<Tool> tools = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            String query = "%" + inquiry + "%";
            stmt.setString(1, query);
            stmt.setString(2, query);
            stmt.setString(3, query);
            stmt.setString(4, query);
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
        }catch (SQLException e) {
        }
        return tools;
    }
    
    /* Method to create SQL prepared statement to remove a tool 
     * after entering tool ID.
     */
    @Override
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
            return "Error removing tool.";
        }
    }
}
