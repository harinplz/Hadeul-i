package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.dto.CommentDto;
import com.ssafy.board.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentMapper mapper;
	
	public CommentServiceImpl(CommentMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<CommentDto> getComments(int boardNo) throws SQLException {

		return mapper.getList(boardNo);
	}

	@Override
	public List<CommentDto> postComment(CommentDto dto) throws SQLException {
		
		mapper.insertComment(dto);
		return getComments(dto.getBoardNo());
		
	}

	@Override
	public List<CommentDto> updateComment(CommentDto dto) throws SQLException {
		
		mapper.updateComment(dto);
		
		return getComments(dto.getBoardNo());
	}

	@Override
	public List<CommentDto> delComment(int boardNo, int commentNo) throws SQLException {
		CommentDto dto = new CommentDto();
		
		dto.setBoardNo(boardNo);
		dto.setCommentNo(commentNo);
		mapper.deleteComment(dto);
		
		return getComments(dto.getBoardNo());
	}

}
