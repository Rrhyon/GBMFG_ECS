package gbmfg_ecs;

import java.util.List;

public interface MaterialDAO {
    void saveMaterial(Material material);
    String saveMaterialUpdates(Material material);
    Material getMaterial(int materialId);
    List<Material> getAllMaterials();
    List<Material> searchMaterials(String inquiry);
    String removeMaterial(int materialId);
}
