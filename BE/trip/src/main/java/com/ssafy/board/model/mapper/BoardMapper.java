package com.ssafy.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.dto.BoardDto;

@Mapper
public interface BoardMapper {

	public List<BoardDto> getBoards() throws SQLException;

	public BoardDto getOne(int boardNo) throws SQLException;

	public int deleteOne(int boardNo) throws SQLException;

	public int updateOne(BoardDto boardDto) throws SQLException;

	public int insertOne(BoardDto boardDto) throws SQLException;

}
