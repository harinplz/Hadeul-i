package com.ssafy.route.model.dto;

import java.util.ArrayList;

public class RouteDto {

	//등록자 / 등록일 / 제목 / 여행 출발 날짜 / 여행 도착 날짜 / 경로 상세 설명 / 선택한 관광지 배열 (위치, 유형, 이름, 주소, 위도, 경도)
	private int travelRouteNo; 
	private String userNo;
	private String regTime;
	private String title;
	private String arriveDate;
	private String departDate;
	private String description;
	
	private ArrayList<RouteListDto> attractionList;
	
	
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public ArrayList<RouteListDto> getAttractionList() {
		return attractionList;
	}

	public void setAttractionList(ArrayList<RouteListDto> attractionList) {
		this.attractionList = attractionList;
	}

	public RouteDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "RouteDto [travelRouteNo=" + travelRouteNo + ", userNo=" + userNo + ", regTime=" + regTime + ", title="
				+ title + ", arriveDate=" + arriveDate + ", departDate=" + departDate + ", description=" + description
				+ ", attractionList=" + attractionList + "]";
	}

	public RouteDto(int travelRouteNo, String userNo, String regTime, String title, String arriveDate,
			String departDate, String description, ArrayList<RouteListDto> attractionList) {
		super();
		this.travelRouteNo = travelRouteNo;
		this.userNo = userNo;
		this.regTime = regTime;
		this.title = title;
		this.arriveDate = arriveDate;
		this.departDate = departDate;
		this.description = description;
		this.attractionList = attractionList;
	}

	public static class RouteListDto{
		
		private String listNo;
		private String travelRouteNo;
		private String attractionName;
		private String address;
		private String latitude;
		private String longitude;
		private String order;
		
		public String getListNo() {
			return listNo;
		}
		public void setListNo(String listNo) {
			this.listNo = listNo;
		}
		public String getTravelRouteNo() {
			return travelRouteNo;
		}
		public void setTravelRouteNo(String travelRouteNo) {
			this.travelRouteNo = travelRouteNo;
		}

		public String getAttractionName() {
			return attractionName;
		}
		public void setAttractionName(String attractionName) {
			this.attractionName = attractionName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
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
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		
		
		public RouteListDto(String listNo, String travelRouteNo, String attractionName, String address, String latitude,
				String longitude, String order) {
			super();
			this.listNo = listNo;
			this.travelRouteNo = travelRouteNo;
			this.attractionName = attractionName;
			this.address = address;
			this.latitude = latitude;
			this.longitude = longitude;
			this.order = order;
		}
		public RouteListDto() {
			super();
		}
		
	}
}
