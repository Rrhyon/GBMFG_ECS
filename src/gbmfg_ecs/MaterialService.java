package gbmfg_ecs;

import java.util.List;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Public Interface class (possibly unnecessary).
 * Date: August 13, 2024
 */
public interface MaterialService {
    void saveMaterial(Material material);
    String saveMaterialUpdates(Material material);
    Material getMaterial(int materialId);
    List<Material> getAllMaterials();
    List<Material> searchMaterials(String inquiry);
    String removeMaterial(int materialId);
}
