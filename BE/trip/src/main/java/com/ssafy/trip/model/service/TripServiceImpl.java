package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dto.TripDto;
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


}
