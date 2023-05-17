package com.ssafy.trip.model.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.TripDto;

@Mapper
public interface TripMapper {
	
	public List<TripDto> selectAll(TripDto dto) throws SQLException;

	public List<TripDto> selectAllReverse(TripDto dto) throws SQLException;

}
