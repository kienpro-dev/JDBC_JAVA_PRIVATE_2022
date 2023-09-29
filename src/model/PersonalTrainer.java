package model;

public class PersonalTrainer {

	private String ptId;
	
	private String ptName;

	private String ptGender;

	private int ptHirePricePerDay;

	public String getPtId() {
		return ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public String getPtGender() {
		return ptGender;
	}

	public void setPtGender(String ptGender) {
		this.ptGender = ptGender;
	}

	public int getPtHirePricePerDay() {
		return ptHirePricePerDay;
	}

	public void setPtHirePricePerDay(int ptHirePricePerDay) {
		this.ptHirePricePerDay = ptHirePricePerDay;
	}
}
