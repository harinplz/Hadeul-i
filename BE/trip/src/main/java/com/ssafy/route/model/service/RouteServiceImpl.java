package com.ssafy.route.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

import com.ssafy.route.model.dto.RouteDto;
import com.ssafy.route.model.dto.RouteDto.RouteListDto;
import com.ssafy.route.model.dto.RouteLike;
import com.ssafy.route.model.mapper.RouteMapper;

@Service
public class RouteServiceImpl implements RouteService {
	
	RouteMapper routeMapper;
	
	public RouteServiceImpl(RouteMapper routeMapper) {
		this.routeMapper = routeMapper;
	}

	@Override
	public int insertRoute(RouteDto routeDto) throws SQLException {
		
		int check = 0;
		check = routeMapper.insertTravleRoute(routeDto);
		
		if(check == 0) {
			throw new SQLException("insertTravleRoute에서 문제가 생겼습니다.");
		}
		
		check = routeMapper.insertRouteList(routeDto);
		
		return check;
	}

	@Override
	public List<RouteListDto> getTravelRouteAll() throws SQLException {
		// TODO Auto-generated method stub
		return routeMapper.getTravelRouteAll();
	}

	@Override
	public RouteDto getTravelRoute(String routeNo) throws SQLException {
		// TODO Auto-generated method stub
		return routeMapper.getTravelRoute(routeNo);
	}

	@Override
	public int countLike(int travleRouteNo) throws SQLException {
		return routeMapper.countLike(travleRouteNo);
	}

	@Override
	public int checkLike(RouteLike routeLike) throws SQLException {
		return routeMapper.checkLike(routeLike);
	}

	@Override
	public int routeGood(RouteLike routeLike) throws SQLException {
		return routeMapper.routeGood(routeLike);
	}

	@Override
	public int routeGoodCancel(RouteLike routeLike) throws SQLException {
		return routeMapper.routeGoodCancel(routeLike);
	}

}
