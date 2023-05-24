package com.ssafy.route.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.route.model.dto.RouteDto;
import com.ssafy.route.model.dto.RouteDto.RouteListDto;
import com.ssafy.route.model.dto.RouteLike;

@Mapper
public interface RouteMapper {

	int insertTravleRoute(RouteDto routeDto) throws SQLException;

	int insertRouteList(RouteDto routeDto) throws SQLException;

	List<RouteListDto> getTravelRouteAll() throws SQLException;

	RouteDto getTravelRoute(String routeNo) throws SQLException;

	int countLike(int traceRouteNo) throws SQLException;

	int checkLike(RouteLike routeLike) throws SQLException;

	int routeGood(RouteLike routeLike) throws SQLException;

	int routeGoodCancel(Map<String, Integer> map) throws SQLException;

}
