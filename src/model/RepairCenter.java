package model;

public class RepairCenter {
    
    private String rcId;
    
    private String rcName;

    private int rcPrice;

    public RepairCenter() {
		super();
	}

	public RepairCenter(String rcId, String rcName, int rcPrice) {
		super();
		this.rcId = rcId;
		this.rcName = rcName;
		this.rcPrice = rcPrice;
	}

	public String getRcId() {
        return rcId;
    }

    public void setRcId(String rcId) {
        this.rcId = rcId;
    }

    public String getRcName() {
        return rcName;
    }

    public void setRcName(String rcName) {
        this.rcName = rcName;
    }

    public int getRcPrice() {
        return rcPrice;
    }

    public void setRcPrice(int rcPrice) {
        this.rcPrice = rcPrice;
    }
}
