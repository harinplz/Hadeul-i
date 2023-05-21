package com.ssafy.hotplace.model.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class HotplaceDto {
	
	private int hotplaceNo;
	private String userNo;
	private String hotplaceName;
	private String hotplaceAddr;
	private String category;
	private String hotplaceContent;
	private String jibun;
	private String latitude;
	private String longitude;
	
	private byte[] img;
	
	public int getHotplaceNo() {
		return hotplaceNo;
	}
	public void setHotplaceNo(int hotplaceNo) {
		this.hotplaceNo = hotplaceNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getHotplaceName() {
		return hotplaceName;
	}
	public void setHotplaceName(String hotplaceName) {
		this.hotplaceName = hotplaceName;
	}
	public String getHotplaceAddr() {
		return hotplaceAddr;
	}
	public void setHotplaceAddr(String hotplaceAddr) {
		this.hotplaceAddr = hotplaceAddr;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getHotplaceContent() {
		return hotplaceContent;
	}
	public void setHotplaceContent(String hotplaceContent) {
		this.hotplaceContent = hotplaceContent;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	
	
	
}
