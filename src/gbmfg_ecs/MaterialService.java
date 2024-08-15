/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface MaterialService {
    String addMaterial(String name, String description, double quantity,
            String unit, int categoryId, int locationId);
    String updateMaterial(int materialId, String name, String description,
            double quantity, String unit, int categoryId, int locationId);
    Material getMaterial(int materialId);
    List<Material> getAllMaterials();
    String removeMaterial(int materialId);
}
