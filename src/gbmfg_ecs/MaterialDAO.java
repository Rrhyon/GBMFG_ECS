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
public interface MaterialDAO {
    String addMaterial(Material material);
    String updateMaterial(Material material);
    Material getMaterial(int materialId);
    List<Material> getAllMaterials();
    String removeMaterial(int materialId);
}
