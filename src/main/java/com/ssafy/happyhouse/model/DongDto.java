package com.ssafy.happyhouse.model;

public class DongDto {
	private String dongcode;
	private String city;
	private String gugun;
	private String dong;
	
	
	public DongDto(String dongcode, String city, String gugun, String dong) {
		super();
		this.dongcode = dongcode;
		this.city = city;
		this.gugun = gugun;
		this.dong = dong;
	}


	@Override
	public String toString() {
		return "DongDto [dongcode=" + dongcode + ", city=" + city + ", gugun=" + gugun + ", dong=" + dong + "]";
	}


	public DongDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getDongcode() {
		return dongcode;
	}


	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getGugun() {
		return gugun;
	}


	public void setGugun(String gugun) {
		this.gugun = gugun;
	}


	public String getDong() {
		return dong;
	}


	public void setDong(String dong) {
		this.dong = dong;
	}
	
	
}
