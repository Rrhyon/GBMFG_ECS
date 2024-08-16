package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class MaterialServiceImpl implements MaterialService{

    private MaterialDAOImpl materialDAO;

    public MaterialServiceImpl() {
        this.materialDAO = new MaterialDAOImpl();
    }

    // Creates the object and returns the object to the DAO method for DB Entry.
    public void saveMaterial(Material material) {
        materialDAO.saveMaterial(material);
    }

    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String saveMaterialUpdates(Material material) {
        return materialDAO.saveMaterialUpdates(material);
    }
    
    // Retrieves the material by ID.
    public Material getMaterial(int materialId) {
        return materialDAO.getMaterial(materialId);
    }

    // Creates a list and retrieves all available materials.
    public List<Material> getAllMaterials() {
        return materialDAO.getAllMaterials();
    }
    
    public List<Material> searchMaterials(String inquiry){
        return materialDAO.searchMaterials(inquiry);
    }
    
    // Removes selected materials.
    public String removeMaterial(int materialId) {
        return materialDAO.removeMaterial(materialId);
    }
}
