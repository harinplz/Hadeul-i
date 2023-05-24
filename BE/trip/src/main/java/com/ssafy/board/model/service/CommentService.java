package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.board.model.dto.CommentDto;

public interface CommentService {

	List<CommentDto> getComments(int boardNo) throws SQLException;

	List<CommentDto> postComment(CommentDto dto) throws SQLException;

	List<CommentDto> updateComment(CommentDto dto) throws SQLException;

	List<CommentDto> delComment(int boardNo, int commentNo) throws SQLException;

}
