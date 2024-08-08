package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public class Tool {

    private int toolId;
    private String name;
    private String description;
    private String condition;
    private boolean isAvailable;
    private String serialNum;
    private int categoryId;
    private int locationId;

    // Constructors, getters, and setters
    public Tool() {
    }

    public Tool(String name, String description, String condition, boolean isAvailable, String serialNum, int categoryId, int locationId) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.isAvailable = isAvailable;
        this.serialNum = serialNum;
        this.categoryId = categoryId;
        this.locationId = locationId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
