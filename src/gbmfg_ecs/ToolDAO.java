package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface ToolDAO {
    String saveTool(Tool tool);
    String updateTool(Tool tool);
    Tool getTool(int toolId);
    List<Tool> getAllTools();
    String removeTool(int toolId);
}
