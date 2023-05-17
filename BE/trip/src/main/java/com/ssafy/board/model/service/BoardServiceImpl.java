package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService{

	BoardMapper mapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<BoardDto> getList() throws SQLException {
		
		return mapper.getBoards();
	}

	@Override
	public BoardDto getOne(int boardNo) throws SQLException {

		return mapper.getOne(boardNo);
	}

	@Override
	public boolean deleteOne(int boardNo) throws SQLException {
		if(mapper.deleteOne(boardNo) == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateOne(BoardDto boardDto) throws SQLException {
		if(mapper.updateOne(boardDto) == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean insertOne(BoardDto boardDto) throws SQLException {
		if(mapper.insertOne(boardDto) == 1) {
			System.out.println("등록완료");
			return true;
		}else {
			System.out.println("등록실패");
			return false;
		}
	}

}
