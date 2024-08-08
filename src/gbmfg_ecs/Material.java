package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public class Material {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
