package com.ssafy.happyhouse.model;

public class CoronaDto {
	private int hospital_id;
	private String gunguCode;
	private String name;
	private String address;
	private String weekdayop;
	private String tel;
	private String lat;
	private String lng;
	private String type;
	public CoronaDto(int hospital_id, String gunguCode, String name, String address, String weekdayop, String tel,
			String lat, String lng, String type) {
		super();
		this.hospital_id = hospital_id;
		this.gunguCode = gunguCode;
		this.name = name;
		this.address = address;
		this.weekdayop = weekdayop;
		this.tel = tel;
		this.lat = lat;
		this.lng = lng;
		this.type = type;
	}
	public CoronaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	public String getGunguCode() {
		return gunguCode;
	}
	public void setGunguCode(String gunguCode) {
		this.gunguCode = gunguCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWeekdayop() {
		return weekdayop;
	}
	public void setWeekdayop(String weekdayop) {
		this.weekdayop = weekdayop;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
