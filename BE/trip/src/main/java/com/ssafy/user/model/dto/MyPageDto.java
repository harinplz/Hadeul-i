package com.ssafy.user.model.dto;

public class MyPageDto {
	private int no;
	private String id;
	private String pw;
	private String name;
	private String email;

	private String communityTitle; // 커뮤니티 게시글
	private String travelRouteTitle; // 여행 경로 게시글
	private String hotplaceTitle; // 커뮤니티 게시
	
	private int communityNo;
	private int travelRouteNo;
	private int hotplaceNo;

	
	public String getCommunityTitle() {
		return communityTitle;
	}

	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
	}

	public String getTravelRouteTitle() {
		return travelRouteTitle;
	}

	public void setTravelRouteTitle(String travelRouteTitle) {
		this.travelRouteTitle = travelRouteTitle;
	}

	public String getHotplaceTitle() {
		return hotplaceTitle;
	}

	public void setHotplaceTitle(String hotplaceTitle) {
		this.hotplaceTitle = hotplaceTitle;
	}

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public int getTravelRouteNo() {
		return travelRouteNo;
	}

	public void setTravelRouteNo(int travelRouteNo) {
		this.travelRouteNo = travelRouteNo;
	}

	public int getHotplaceNo() {
		return hotplaceNo;
	}

	public void setHotplaceNo(int hotplaceNo) {
		this.hotplaceNo = hotplaceNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MyPageDto(int no, String id, String pw, String name, String email, String communityTitle,
			String travelRouteTitle, String hotplaceTitle, int communityNo, int travelRouteNo, int hotplaceNo) {
		super();
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.communityTitle = communityTitle;
		this.travelRouteTitle = travelRouteTitle;
		this.hotplaceTitle = hotplaceTitle;
		this.communityNo = communityNo;
		this.travelRouteNo = travelRouteNo;
		this.hotplaceNo = hotplaceNo;
	}
	public MyPageDto() {}
}
