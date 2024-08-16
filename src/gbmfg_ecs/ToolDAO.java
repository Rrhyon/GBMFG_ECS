package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface ToolDAO {
    String saveTool(Tool tool);
    String saveToolUpdates(Tool tool);
    Tool getTool(int toolId);
    List<Tool> getAllTools();
    List<Tool> searchTools(String inquiry);
    String removeTool(int toolId);
}
