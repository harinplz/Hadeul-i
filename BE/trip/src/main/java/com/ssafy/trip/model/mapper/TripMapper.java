package com.ssafy.trip.model.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.AttractionLikeDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchCondition;

@Mapper
public interface TripMapper {
	
	public List<TripDto> selectAll(TripDto dto) throws SQLException;

	public List<TripDto> selectAllReverse(TripDto dto) throws SQLException;

	public List<TripDto> selectAttractions(TripSearchCondition dto) throws SQLException;
	
	public List<TripDto> selectUserAttractions(TripSearchCondition dto) throws SQLException;

	public int getCount1() throws SQLException;
	
	public int getCount2() throws SQLException;

	public List<TripDto> getTripSearchResult(String title) throws SQLException;

	public int checkLike1(AttractionLikeDto attractionLike) throws SQLException;

	public int attractionGood1(AttractionLikeDto attractionLike) throws SQLException;

	public int attractionGoodCancel1(AttractionLikeDto dto) throws SQLException;

	public int countLike1(AttractionLikeDto dto) throws SQLException;
	
	public int checkLike2(AttractionLikeDto attractionLike) throws SQLException;

	public int attractionGood2(AttractionLikeDto attractionLike) throws SQLException;

	public int attractionGoodCancel2(AttractionLikeDto dto) throws SQLException;

	public int countLike2(AttractionLikeDto dto) throws SQLException;

	public String getDescription1(AttractionLikeDto dto) throws SQLException;
	
	public String getDescription2(AttractionLikeDto dto) throws SQLException;
}
