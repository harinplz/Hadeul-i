package com.ssafy.route.model.dto;

public class RouteLike {
	
	private int travelRouteNo;
	private int userNo;
	

	public int getTravelRouteNo() {
		return travelRouteNo;
	}
	public void setTravelRouteNo(int travelRouteNo) {
		this.travelRouteNo = travelRouteNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	
	public RouteLike(int travelRouteNo, int userNo) {
		super();
		this.travelRouteNo = travelRouteNo;
		this.userNo = userNo;
	}
	public RouteLike() {}
}
