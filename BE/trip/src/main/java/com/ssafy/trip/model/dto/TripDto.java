package com.ssafy.trip.model.dto;

public class TripDto {
	
	private String attractionNo;
	private String category;
	private String attractionName;
	private String address;
	private String imageUrl;
	private String sido;
	private String gugun;
	private String latitude;
	private String longitude;
	private String attractionType;
	private String searchKeyWord;
	private String hit;
	
	
	
	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getAttractionNo() {
		return attractionNo;
	}
	
	public void setAttractionNo(String attractionNo) {
		this.attractionNo = attractionNo;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
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
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getSido() {
		return sido;
	}
	
	public void setSido(String sido) {
		this.sido = sido;
	}
	
	public String getGugun() {
		return gugun;
	}
	
	public void setGugun(String gugun) {
		this.gugun = gugun;
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
	
	public String getAttractionType() {
		return attractionType;
	}
	
	public void setAttractionType(String attractionType) {
		this.attractionType = attractionType;
	}
	
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	
	public TripDto(String attractionNo, String category, String attractionName, String address, String imageUrl,
			String sido, String gugun, String latitude, String longitude, String attractionType, String searchKeyWord) {
		
		this.attractionNo = attractionNo;
		this.category = category;
		this.attractionName = attractionName;
		this.address = address;
		this.imageUrl = imageUrl;
		this.sido = sido;
		this.gugun = gugun;
		this.latitude = latitude;
		this.longitude = longitude;
		this.attractionType = attractionType;
		this.searchKeyWord = searchKeyWord;
	}
	
	public TripDto() {}
	
	@Override
	public String toString() {
		return "TripDto [attractionNo=" + attractionNo + ", category=" + category + ", attractionName=" + attractionName
				+ ", address=" + address + ", imageUrl=" + imageUrl + ", sido=" + sido + ", gugun=" + gugun
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", attractionType=" + attractionType
				+ ", searchKeyWord=" + searchKeyWord + "]";
	}
}
