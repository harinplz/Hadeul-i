package com.ssafy.trip.model.dto;

public class TripContentDto {
	private String attractionNo;
	private String tableType;
	private String content;
	
	public String getAttractionNo() {
		return attractionNo;
	}
	
	public void setAttractionNo(String attractionNo) {
		this.attractionNo = attractionNo;
	}
	
	public String getTableType() {
		return tableType;
	}
	
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public TripContentDto(String attractionNo, String tableType, String content) {
		this.attractionNo = attractionNo;
		this.tableType = tableType;
		this.content = content;
	}
	
	public TripContentDto() {}

}