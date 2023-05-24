package com.ssafy.board.model.dto;

public class CommentDto {
	private int boardNo;
	private int commentNo;
	private int userNo;
	private String userName; // 댓글 등록 이름이 보이게..?
	private String registDate;
	private String comment;

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CommentDto(int boardNo, int commentNo, int userNo, String userName, String registDate, String comment) {
		super();
		this.boardNo = boardNo;
		this.commentNo = commentNo;
		this.userNo = userNo;
		this.userName = userName;
		this.registDate = registDate;
		this.comment = comment;
	}

	public CommentDto() {
	}

}
