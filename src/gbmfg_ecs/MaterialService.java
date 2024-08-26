package gbmfg_ecs;

import java.util.List;

public interface MaterialService {
    String saveMaterial(Material material);  // Changed from void to String
    String saveMaterialUpdates(Material material);
    Material getMaterial(int materialId);
    List<Material> getAllMaterials();
    List<Material> searchMaterials(String inquiry);
    String removeMaterial(int materialId);
}
