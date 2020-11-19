package com.ssafy.happyhouse.model;

public class EnvironmentDto {
	private String cname;
	private String lnumber;
	private String scode;
	private String sname; 
	private String mchkdate;
	private String chkagency;
	private String chkagencyname;
	private String mapgubun;
	private String isdiposal;
	private String chkspec;
	private String chkresult;
	private String locroadaddr;
	private String locaddr;
	
	
	
	public EnvironmentDto(String cname, String lnumber, String scode, String sname, String mchkdate, String chkagency,
			String chkagencyname, String mapgubun, String isdiposal, String chkspec, String chkresult,
			String locroadaddr, String locaddr) {
		super();
		this.cname = cname;
		this.lnumber = lnumber;
		this.scode = scode;
		this.sname = sname;
		this.mchkdate = mchkdate;
		this.chkagency = chkagency;
		this.chkagencyname = chkagencyname;
		this.mapgubun = mapgubun;
		this.isdiposal = isdiposal;
		this.chkspec = chkspec;
		this.chkresult = chkresult;
		this.locroadaddr = locroadaddr;
		this.locaddr = locaddr;
	}
	public EnvironmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EnvironmentDAO [cname=" + cname + ", lnumber=" + lnumber + ", scode=" + scode + ", sname=" + sname
				+ ", mchkdate=" + mchkdate + ", chkagency=" + chkagency + ", chkagencyname=" + chkagencyname
				+ ", mapgubun=" + mapgubun + ", isdiposal=" + isdiposal + ", chkspec=" + chkspec + ", chkresult="
				+ chkresult + ", locroadaddr=" + locroadaddr + ", locaddr=" + locaddr + "]";
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLnumber() {
		return lnumber;
	}
	public void setLnumber(String lnumber) {
		this.lnumber = lnumber;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getMchkdate() {
		return mchkdate;
	}
	public void setMchkdate(String mchkdate) {
		this.mchkdate = mchkdate;
	}
	public String getChkagency() {
		return chkagency;
	}
	public void setChkagency(String chkagency) {
		this.chkagency = chkagency;
	}
	public String getChkagencyname() {
		return chkagencyname;
	}
	public void setChkagencyname(String chkagencyname) {
		this.chkagencyname = chkagencyname;
	}
	public String getMapgubun() {
		return mapgubun;
	}
	public void setMapgubun(String mapgubun) {
		this.mapgubun = mapgubun;
	}
	public String getIsdiposal() {
		return isdiposal;
	}
	public void setIsdiposal(String isdiposal) {
		this.isdiposal = isdiposal;
	}
	public String getChkspec() {
		return chkspec;
	}
	public void setChkspec(String chkspec) {
		this.chkspec = chkspec;
	}
	public String getChkresult() {
		return chkresult;
	}
	public void setChkresult(String chkresult) {
		this.chkresult = chkresult;
	}
	public String getLocroadaddr() {
		return locroadaddr;
	}
	public void setLocroadaddr(String locroadaddr) {
		this.locroadaddr = locroadaddr;
	}
	public String getLocaddr() {
		return locaddr;
	}
	public void setLocaddr(String locaddr) {
		this.locaddr = locaddr;
	}
}