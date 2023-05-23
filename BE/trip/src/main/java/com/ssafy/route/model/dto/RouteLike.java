package com.ssafy.route.model.dto;

public class RouteLike {
	
	private int travleRouteNo;
	private int userNo;
	
	public int getTravleRouteNo() {
		return travleRouteNo;
	}
	public void setTravleRouteNo(int travleRouteNo) {
		this.travleRouteNo = travleRouteNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public RouteLike(int travleRouteNo, int userNo) {
		super();
		this.travleRouteNo = travleRouteNo;
		this.userNo = userNo;
	}
	
	public RouteLike() {}
}
