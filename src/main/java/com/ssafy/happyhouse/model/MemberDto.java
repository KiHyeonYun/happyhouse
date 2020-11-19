package com.ssafy.happyhouse.model;

public class MemberDto {
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_address;
	private String member_joindate;
	
	public MemberDto() {	}

	public MemberDto(String member_id, String member_pw, String member_name, String member_address,
			String member_joindate) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_address = member_address;
		this.member_joindate = member_joindate;
	}

	@Override
	public String toString() {
		return "MemberDto [member_id=" + member_id + ", member_pw=" + member_pw + ", member_name=" + member_name
				+ ", member_address=" + member_address + ", member_joindate=" + member_joindate + "]";
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_joindate() {
		return member_joindate;
	}
	public void setMember_joindate(String member_joindate) {
		this.member_joindate = member_joindate;
	}
}
