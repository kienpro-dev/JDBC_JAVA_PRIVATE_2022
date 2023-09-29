package model;

public class MemberPT {

    private Member mem;

    private PersonalTrainer pt;

    private int timeLeft;

    private int income; // use to set income of PT using Group By 

    private int numberOfMember; // use to set number of members of each PT using Group By

    public Member getMem() {
        return mem;
    }

    public void setMem(Member mem) {
        this.mem = mem;
    }

    public PersonalTrainer getPt() {
        return pt;
    }

    public void setPt(PersonalTrainer pt) {
        this.pt = pt;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getAllPrice() {
        return getTimeLeft() * pt.getPtHirePricePerDay();
    }

    public int getIncome() {
        return income;
    }
    
    public void setIncome(int income) {
        this.income = income;
    }

    public int getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

}
