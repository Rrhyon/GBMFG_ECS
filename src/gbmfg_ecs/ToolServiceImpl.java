package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;


public class ToolServiceImpl implements ToolService {

    private ToolDAOImpl toolDAOImpl;

    public ToolServiceImpl() {
        this.toolDAOImpl = new ToolDAOImpl();
    }

    // Creates the object and returns the object to the DAO method for DB Entry.
    public void saveTool(Tool tool){
        toolDAOImpl.saveTool(tool);
    }
    
    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public void updateTool(Tool tool){
        toolDAOImpl.updateTool(tool);
    }

    // Retrieves the tool record by ID.
    public Tool getTool(int toolId) {
        return toolDAOImpl.getTool(toolId);
    }

    // Creates a list and retrieves all available tools.
    public List<Tool> getAllTools() {
        return toolDAOImpl.getAllTools();
    }
     
    // Removes selected tools.
    public String removeTool(int toolId) {
        return toolDAOImpl.removeTool(toolId);
    }
}
