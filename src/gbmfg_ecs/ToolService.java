package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.util.List;

public class ToolService {

    private ToolDAO toolDAO;

    public ToolService() {
        this.toolDAO = new ToolDAO();
    }

    public String addTool(String name, String description, String condition, boolean isAvailable, String serialNum, int categoryId, int locationId) {
        Tool tool = new Tool(name, description, condition, isAvailable, serialNum, categoryId, locationId);
        return toolDAO.addTool(tool);
    }

    public String removeTool(int toolId) {
        return toolDAO.removeTool(toolId);
    }

    public Tool getTool(int toolId) {
        return toolDAO.getTool(toolId);
    }

    public String updateTool(int toolId, String name, String description, String condition, boolean isAvailable, String serialNum, int categoryId, int locationId) {
        Tool tool = new Tool(name, description, condition, isAvailable, serialNum, categoryId, locationId);
        tool.setToolId(toolId);
        return toolDAO.updateTool(tool);
    }

    public List<Tool> getAllTools() {
        return toolDAO.getAllTools();
    }
}
