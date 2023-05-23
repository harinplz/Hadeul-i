package com.ssafy.trip.model.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

}
