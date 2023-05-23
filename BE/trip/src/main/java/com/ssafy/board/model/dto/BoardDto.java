package com.ssafy.board.model.dto;

public class BoardDto {
	private int boardNo;
	private int userNo;
	private String title;
	private String content;
	private String regtime;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public BoardDto(int boardNo, int userNo, String title, String content, String regtime) {
		super();
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
	}
	public BoardDto() {
		super();
	}
	@Override
	public String toString() {
		return "BoardDto [boardNo=" + boardNo + ", userNo=" + userNo + ", title=" + title + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}
	

	
}
