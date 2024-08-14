package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class MaterialService {

    private MaterialDAO materialDAO;

    public MaterialService() {
        this.materialDAO = new MaterialDAO();
    }

    // Creates the object and returns the object to the DAO method for DB Entry.
    public String addMaterial(String name, String description, double quantity,
            String unit, int categoryId, int locationId) {
        Material material = new Material(name, description, quantity, unit, 
                categoryId, locationId);
        return materialDAO.addMaterial(material);
    }

    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String updateMaterial(int materialId, String name, String description,
            double quantity, String unit, int categoryId, int locationId) {
        Material material = new Material(name, description, quantity, unit, 
                categoryId, locationId);
        material.setMaterialId(materialId);
        return materialDAO.updateMaterial(material);
    }
    
    // Retrieves the material by ID.
    public Material getMaterial(int materialId) {
        return materialDAO.getMaterial(materialId);
    }

    // Creates a list and retrieves all available materials.
    public List<Material> getAllMaterials() {
        return materialDAO.getAllMaterials();
    }
    
    // Removes selected materials.
    public String removeMaterial(int materialId) {
        return materialDAO.removeMaterial(materialId);
    }
}
