package com.ssafy.trip.model.dto;

public class TripDto {
	
	private String sido;
	private String contentId;
	private String title;
	private String latitue;
	private String longitude;
	private String addr1;
	private String searchKeyWord;

	public TripDto() {}

	public TripDto(String sido, String contentId, String title, String latitue, String longitude, String addr1,
			String searchKeyWord) {
		super();
		this.sido = sido;
		this.contentId = contentId;
		this.title = title;
		this.latitue = latitue;
		this.longitude = longitude;
		this.addr1 = addr1;
		this.searchKeyWord = searchKeyWord;
	}


	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLatitue() {
		return latitue;
	}

	public void setLatitue(String latitue) {
		this.latitue = latitue;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

	@Override
	public String toString() {
		return "TripDto [sido=" + sido + ", contentId=" + contentId + ", title=" + title + ", latitue=" + latitue
				+ ", longitude=" + longitude + ", addr1=" + addr1 + ", searchKeyWord=" + searchKeyWord + "]";
	}
	
}
