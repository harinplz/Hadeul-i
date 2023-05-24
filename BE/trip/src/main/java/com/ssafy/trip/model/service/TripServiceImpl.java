package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dto.AttractionLikeDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchCondition;
import com.ssafy.trip.model.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService {
	
	private TripMapper tripMapper;

	public TripServiceImpl(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}


	@Override
	public List<TripDto> list(TripDto dto, int sort) throws SQLException {
		List<TripDto> res = null;
		if(sort == 1) {//이름순
			res = tripMapper.selectAll(dto);
		}else {//역 이름 순 정렬
			res = tripMapper.selectAllReverse(dto);
		}
		
		return res;
	}


	@Override
	public List<TripDto> attractionList(TripSearchCondition dto) throws SQLException {
		if(dto.getTableType().equals("1")) {
			return tripMapper.selectAttractions(dto);
		}else {
			return tripMapper.selectUserAttractions(dto);
		}	
	}


	@Override
	public int getCount(String tableType) throws SQLException {
		if(tableType.equals("1")) {
			return tripMapper.getCount1();
		}else {
			return tripMapper.getCount2();
		}
		
	}

	@Override
	public List<TripDto> getSearchResult(String title) throws SQLException {
		return tripMapper.getTripSearchResult(title);
	}

	/*
	 * 좋아요 관련 메서드들 시작
	 * */
	@Override
	public int checkLike(AttractionLikeDto attractionLike) throws SQLException {

		if(attractionLike.getAttractionType().equals("1")) {
			return tripMapper.checkLike1(attractionLike);
		}else {
			return tripMapper.checkLike2(attractionLike);
		}
		
	}


	@Override
	public int attractionGood(AttractionLikeDto attractionLike) throws SQLException {
		
		if(attractionLike.getAttractionType().equals("1")) {
			return tripMapper.attractionGood1(attractionLike);
		}else {
			return tripMapper.attractionGood2(attractionLike);
		}
	}


	@Override
	public int attractionGoodCancel(AttractionLikeDto dto) throws SQLException {

		if(dto.getAttractionType().equals("1")) {
			return tripMapper.attractionGoodCancel1(dto);
		}else {
			return tripMapper.attractionGoodCancel2(dto);
		}
	}


	@Override
	public int countLike(AttractionLikeDto dto) throws SQLException {
		if(dto.getAttractionType().equals("1")) {
			return tripMapper.countLike1(dto);
		}else {
			return tripMapper.countLike2(dto);
		}
	}


	@Override
	public String getDescription(AttractionLikeDto dto) throws SQLException {
		
		if(dto.getAttractionType().equals("1")) {
			return tripMapper.getDescription1(dto);
		}else {
			return tripMapper.getDescription2(dto);
		}
	}
}
