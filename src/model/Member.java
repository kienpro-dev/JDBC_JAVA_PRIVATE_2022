package model;

import java.sql.Date;

public class Member {

    private String memberId;

    private String memberName;

    private int memberAge;

    private Date memberDayStart;

    private Date memberDayEnd;

    private String username;

    public Member() {
		super();
	}

	public Member(String memberId, String memberName, int memberAge, Date memberDayStart, Date memberDayEnd,
			String username) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.memberDayStart = memberDayStart;
		this.memberDayEnd = memberDayEnd;
		this.username = username;
	}

	public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }

    public Date getMemberDayStart() {
        return memberDayStart;
    }

    public void setMemberDayStart(Date memberDayStart) {
        this.memberDayStart = memberDayStart;
    }

    public Date getMemberDayEnd() {
        return memberDayEnd;
    }

    public void setMemberDayEnd(Date newDayEnd) {
        this.memberDayEnd =  newDayEnd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
