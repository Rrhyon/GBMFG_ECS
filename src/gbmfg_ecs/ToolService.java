package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface ToolService {
    void saveTool(Tool tool);
    void updateTool(Tool tool);
    Tool getTool(int toolId);
    List<Tool> getAllTools();
    String removeTool(int toolId);
}
    