package com.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.dto.CommentDto;

@Mapper
public interface CommentMapper {

	List<CommentDto> getList(int boardNo) throws SQLException;

	void insertComment(CommentDto dto) throws SQLException;

	void updateComment(CommentDto dto) throws SQLException;

	void deleteComment(CommentDto dto) throws SQLException;

}
