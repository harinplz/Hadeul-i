package com.ssafy.trip.model.service;

import java.sql.SQLException;

import java.util.List;

import com.ssafy.trip.model.dto.AttractionLikeDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchCondition;


public interface TripService {
	
	public List<TripDto> list(TripDto dto, int sort) throws SQLException;

	public List<TripDto> attractionList(TripSearchCondition dto) throws SQLException;

	public int getCount(String tableType) throws SQLException;

	public List<TripDto> getSearchResult(String title) throws SQLException;

	public int checkLike(AttractionLikeDto attractionLike) throws SQLException;

	public int attractionGood(AttractionLikeDto attractionLike) throws SQLException;

	public int attractionGoodCancel(AttractionLikeDto dto) throws SQLException;

	public int countLike(AttractionLikeDto dto) throws SQLException;

	public String getDescription(AttractionLikeDto dto) throws SQLException;

}
