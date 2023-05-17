package com.ssafy.board.model.dto;

public class BoardDto {
	private int boardNo;
	private String userId;
	private String title;
	private String content;
	private String regtime;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public BoardDto(int boardNo, String userId, String title, String content, String regtime) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
	}
	public BoardDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "BoardDto [board_no=" + boardNo + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", regtime=" + regtime + "]";
	}
	
	
}
