package model;

public class Equipment {
    
    private String equipmentId;
    
    private String equipmentName;

    private int equipmentDurability;

    public String getEquipmentId() {
        return equipmentId;
    }

    public Equipment(String equipmentId, String equipmentName, int equipmentDurability) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.equipmentDurability = equipmentDurability;
	}

	public Equipment() {
		super();
	}

	public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquipmentDurability() {
        return equipmentDurability;
    }

    public void setEquipmentDurability(int equipmentDurability) {
        this.equipmentDurability = equipmentDurability;
    }

    
}
