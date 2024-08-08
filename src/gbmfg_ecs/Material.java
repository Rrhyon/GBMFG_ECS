/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import javax.persistence.*;

/**
 *
 * @author phillip.tette
 */
@Entity
@Table(name = "materials")
public class Material implements InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int materialId;

    private String name;
    private String description;
    private double quantity;
    private String unit;

    // Constructors, getters, and setters
    public Material() {
    }

    public Material(String name, String description, double quantity, String unit) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getStatus() {
        return quantity > 0 ? "In Stock" : "Out of Stock";
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
