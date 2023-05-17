package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.board.model.dto.BoardDto;

public interface BoardService {

	List<BoardDto> getList() throws SQLException;

	BoardDto getOne(int boardNo) throws SQLException;

	boolean deleteOne(int boardNo) throws SQLException;

	boolean updateOne(BoardDto boardDto) throws SQLException;

	boolean insertOne(BoardDto boardDto) throws SQLException;
}
