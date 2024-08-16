package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface MaterialDAO {
    String saveMaterial(Material material);
    String saveMaterialUpdates(Material material);
    Material getMaterial(int materialId);
    List<Material> getAllMaterials();
    List<Material> searchMaterials(String inquiry);
    String removeMaterial(int materialId);
}
