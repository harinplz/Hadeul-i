package com.ssafy.route.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.route.model.dto.RouteDto;
import com.ssafy.route.model.dto.RouteDto.RouteListDto;
import com.ssafy.route.model.dto.RouteLike;

public interface RouteService {

	int insertRoute(RouteDto routeDto) throws SQLException;

	List<RouteListDto> getTravelRouteAll() throws SQLException;

	RouteDto getTravelRoute(String routeNo) throws SQLException;

	int countLike(int traceRouteNo)throws SQLException;

	int checkLike(RouteLike routeLike) throws SQLException;

	int routeGood(RouteLike routeLike) throws SQLException;

	int routeGoodCancel(RouteLike routeLike) throws SQLException;

}
