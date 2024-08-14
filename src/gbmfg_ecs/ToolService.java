package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class ToolService {

    private ToolDAO toolDAO;

    public ToolService() {
        this.toolDAO = new ToolDAO();
    }

    // Creates the object and returns the object to the DAO method for DB Entry.
    public String addTool(String name, String description, String condition, 
            boolean isAvailable, String serialNum, int categoryId, 
            int locationId) {
        Tool tool = new Tool(name, description, condition, isAvailable, 
                serialNum, categoryId, locationId);
        return toolDAO.addTool(tool);
    }
    
    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String updateTool(int toolId, String name, String description, 
            String condition, boolean isAvailable, String serialNum, 
            int categoryId, int locationId) {
        Tool tool = new Tool(name, description, condition, isAvailable, 
                serialNum, categoryId, locationId);
        tool.setToolId(toolId);
        return toolDAO.updateTool(tool);
    }

    // Retrieves the tool record by ID.
    public Tool getTool(int toolId) {
        return toolDAO.getTool(toolId);
    }

    // Creates a list and retrieves all available tools.
    public List<Tool> getAllTools() {
        return toolDAO.getAllTools();
    }
    
    // Removes selected tools.
    public String removeTool(int toolId) {
        return toolDAO.removeTool(toolId);
    }
}
