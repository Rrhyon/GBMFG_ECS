package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class MaterialServiceImpl implements MaterialService {
    private MaterialDAO materialDAO;

    public MaterialServiceImpl() {
        this.materialDAO = new MaterialDAOImpl();
    }

    @Override
    public String saveMaterial(Material material) {
        materialDAO.saveMaterial(material);
        return "Material saved successfully."; // Return a success message or any relevant string
    }

    @Override
    public String saveMaterialUpdates(Material material) {
        return materialDAO.saveMaterialUpdates(material);
    }

    @Override
    public Material getMaterial(int materialId) {
        return materialDAO.getMaterial(materialId);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialDAO.getAllMaterials();
    }

    @Override
    public List<Material> searchMaterials(String inquiry) {
        return materialDAO.searchMaterials(inquiry);
    }

    @Override
    public String removeMaterial(int materialId) {
        return materialDAO.removeMaterial(materialId);
    }
}