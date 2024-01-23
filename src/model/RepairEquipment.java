package model;

public class RepairEquipment {
    
    private Equipment e;

    private RepairCenter rc;

    private int timeLeft;

    public Equipment getE() {
        return e;
    }

    public RepairEquipment() {
		super();
	}

	public RepairEquipment(Equipment e, RepairCenter rc, int timeLeft) {
		super();
		this.e = e;
		this.rc = rc;
		this.timeLeft = timeLeft;
	}

	public void setE(Equipment e) {
        this.e = e;
    }

    public RepairCenter getRc() {
        return rc;
    }

    public void setRc(RepairCenter rc) {
        this.rc = rc;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
