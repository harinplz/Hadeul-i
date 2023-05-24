package com.ssafy.trip.model.dto;

public class AttractionLikeDto {
	
	private String attractionNo;
	private String attractionType;
	private int userNo;
	
	public String getAttractionNo() {
		return attractionNo;
	}
	
	public void setAttractionNo(String attractionNo) {
		this.attractionNo = attractionNo;
	}
	
	public String getAttractionType() {
		return attractionType;
	}
	
	public void setAttractionType(String attractionType) {
		this.attractionType = attractionType;
	}
	
	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public AttractionLikeDto(String attractionNo, String attractionType, int userNo) {
		super();
		this.attractionNo = attractionNo;
		this.attractionType = attractionType;
		this.userNo = userNo;
	}
	
	public AttractionLikeDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "AttractionLike [attractionNo=" + attractionNo + ", attractionType=" + attractionType + ", userNo="
				+ userNo + "]";
	}
}
