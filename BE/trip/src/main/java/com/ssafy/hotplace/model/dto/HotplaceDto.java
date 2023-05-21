package com.ssafy.hotplace.model.dto;

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
	
	private String img;
	private String orgImg;
	
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getOrgImg() {
		return orgImg;
	}
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}
	
	public HotplaceDto() {
		super();
	}
	
	public HotplaceDto(int hotplaceNo, String userNo, String hotplaceName, String hotplaceAddr, String category,
			String hotplaceContent, String jibun, String latitude, String longitude, String img, String orgImg) {
		super();
		this.hotplaceNo = hotplaceNo;
		this.userNo = userNo;
		this.hotplaceName = hotplaceName;
		this.hotplaceAddr = hotplaceAddr;
		this.category = category;
		this.hotplaceContent = hotplaceContent;
		this.jibun = jibun;
		this.latitude = latitude;
		this.longitude = longitude;
		this.img = img;
		this.orgImg = orgImg;
	}
	
	
	@Override
	public String toString() {
		return "HotplaceDto [hotplaceNo=" + hotplaceNo + ", userNo=" + userNo + ", hotplaceName=" + hotplaceName
				+ ", hotplaceAddr=" + hotplaceAddr + ", category=" + category + ", hotplaceContent=" + hotplaceContent
				+ ", jibun=" + jibun + ", latitude=" + latitude + ", longitude=" + longitude + ", img=" + img
				+ ", orgImg=" + orgImg + "]";
	}
	
	
	
	
	
	
}
