package com.ssafy.trip.model.service;

import java.sql.SQLException;

import java.util.List;

import com.ssafy.trip.model.dto.TripDto;


public interface TripService {
	
	public List<TripDto> list(TripDto dto, int sort) throws SQLException;

}
