package com.ssafy.trip.model.dto;

public class TripSearchCondition {
	
	private int countPerPage = 20;
	private String tableType;
	private String sido;
	private String category;
	private String searchKeyWord;
	private int currentPage = 1; //기본 페이지 : 1(offset 계산을 위해..)
	
	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public int getCountPerPage() {
		return countPerPage;
	}
	
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	
	public int getOffset() {
		return (this.currentPage - 1) * countPerPage;
	}

	public String getSido() {
		return sido;
	}
	
	public void setSido(String sido) {
		this.sido = sido;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public TripSearchCondition(int countPerPage, String sido, String category, String searchKeyWord, int currentPage, String tableType) {
		this.countPerPage = countPerPage;
		this.sido = sido;
		this.category = category;
		this.searchKeyWord = searchKeyWord;
		this.currentPage = currentPage;
		this.tableType = tableType;
	}
	
	public TripSearchCondition() {}
	
}
