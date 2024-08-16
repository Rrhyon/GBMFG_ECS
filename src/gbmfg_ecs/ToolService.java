package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface ToolService {
    void saveTool(Tool tool);
    String saveToolUpdates(Tool tool);
    Tool getTool(int toolId);
    List<Tool> getAllTools();
    List<Tool> searchTools(String inquiry);
    String removeTool(int toolId);
}
    