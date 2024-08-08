package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.util.List;

public class MaterialService {

    private MaterialDAO materialDAO;

    public MaterialService() {
        this.materialDAO = new MaterialDAO();
    }

    public String addMaterial(String name, String description, double quantity, String unit) {
        Material material = new Material(name, description, quantity, unit);
        return materialDAO.addMaterial(material);
    }

    public String removeMaterial(int materialId) {
        return materialDAO.removeMaterial(materialId);
    }

    public Material getMaterial(int materialId) {
        return materialDAO.getMaterial(materialId);
    }

    public String updateMaterial(int materialId, String name, String description, double quantity, String unit) {
        Material material = new Material(name, description, quantity, unit);
        material.setMaterialId(materialId);
        return materialDAO.updateMaterial(material);
    }

    public List<Material> getAllMaterials() {
        return materialDAO.getAllMaterials();
    }
}
