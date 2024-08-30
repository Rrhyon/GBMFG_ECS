package gbmfg_ecs;

import java.util.List;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Public Interface class (possibly unnecessary).
 * Date: August 13, 2024
 */
public interface ToolService {
    void saveTool(Tool tool);
    String saveToolUpdates(Tool tool);
    Tool getTool(int toolId);
    List<Tool> getAllTools();
    List<Tool> searchTools(String inquiry);
    String removeTool(int toolId);
}
    