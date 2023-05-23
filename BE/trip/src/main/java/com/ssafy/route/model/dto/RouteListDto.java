package com.ssafy.route.model.dto;

public class RouteListDto {
	
	private int travelRouteNo;
	private String userNo;
	private String regTime;
	private String title;
	private String arriveDate;
	private String departDate;
	private String description;
	
	public int getTravelRouteNo() {
		return travelRouteNo;
	}
	public void setTravelRouteNo(int travelRouteNo) {
		this.travelRouteNo = travelRouteNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	
	public RouteListDto(int travelRouteNo, String userNo, String regTime, String title, String arriveDate,
			String departDate) {
		super();
		this.travelRouteNo = travelRouteNo;
		this.userNo = userNo;
		this.regTime = regTime;
		this.title = title;
		this.arriveDate = arriveDate;
		this.departDate = departDate;
	}
	
	public RouteListDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "RouteListDto [travelRouteNo=" + travelRouteNo + ", userNo=" + userNo + ", regTime=" + regTime
				+ ", title=" + title + ", arriveDate=" + arriveDate + ", departDate=" + departDate + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
