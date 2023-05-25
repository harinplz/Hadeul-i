package com.ssafy.hotplace.model.dto;

public class HotplaceLike {
	
	private int hotplaceNo;
	private int userNo;
	
	public HotplaceLike(int hotplaceNo, int userNo) {
		super();
		this.hotplaceNo = hotplaceNo;
		this.userNo = userNo;
	}
	
	public HotplaceLike() {}
	
	public int getHotplaceNo() {
		return hotplaceNo;
	}
	public void setHotplaceNo(int hotplaceNo) {
		this.hotplaceNo = hotplaceNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	@Override
	public String toString() {
		return "HotplaceLike [hotplaceNo=" + hotplaceNo + ", userNo=" + userNo + "]";
	}
}
