package com.ssafy.happyhouse.model;

public class CommDto {
	private String storeName;
	private String bigClass;
	private String smallClass;
	private String gunguCode;
	private String dongName;
	private String lng;
	private String lat;
	public CommDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "CommDto [storeName=" + storeName + ", bigClass=" + bigClass + ", smallClass=" + smallClass
				+ ", gunguCode=" + gunguCode + ", dongName=" + dongName + ", lng=" + lng + ", lat=" + lat + "]";
	}

	public CommDto(String storeName, String bigClass, String smallClass, String gunguCode, String dongName, String lng,
			String lat) {
		super();
		this.storeName = storeName;
		this.bigClass = bigClass;
		this.smallClass = smallClass;
		this.gunguCode = gunguCode;
		this.dongName = dongName;
		this.lng = lng;
		this.lat = lat;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getBigClass() {
		return bigClass;
	}
	public void setBigClass(String bigClass) {
		this.bigClass = bigClass;
	}
	public String getSmallClass() {
		return smallClass;
	}
	public void setSmallClass(String smallClass) {
		this.smallClass = smallClass;
	}
	public String getGunguCode() {
		return gunguCode;
	}
	public void setGunguCode(String gunguCode) {
		this.gunguCode = gunguCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
}
