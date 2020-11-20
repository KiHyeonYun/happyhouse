package com.ssafy.happyhouse.model;

public class MemberDto {
	private String userid;
	private String userpwd;
	private String username;
	private String useraddress;
	private String userjoindate;
	private String useremail;
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDto(String userid, String userpwd, String username, String useraddress, String userjoindate,
			String useremail) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.username = username;
		this.useraddress = useraddress;
		this.userjoindate = userjoindate;
		this.useremail = useremail;
	}
	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", useraddress="
				+ useraddress + ", userjoindate=" + userjoindate + ", useremail=" + useremail + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getUserjoindate() {
		return userjoindate;
	}
	public void setUserjoindate(String userjoindate) {
		this.userjoindate = userjoindate;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
		
}
