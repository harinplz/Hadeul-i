package com.ssafy.route.controller;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.route.model.dto.RouteDto;
import com.ssafy.route.model.dto.RouteDto.RouteListDto;
import com.ssafy.route.model.dto.RouteLike;
import com.ssafy.route.model.service.RouteService;

@RestController
public class RouteController {
	private RouteService routeService;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	// 여행 경로 등록 요청을 받는 메서드
	@PostMapping("/route")
	public ResponseEntity<?> routeRegist(@RequestBody RouteDto routeDto) {

		int res = 0;

		try {
			res = routeService.insertRoute(routeDto);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res != 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}

	// 여행 경로 리스트들의 요청을 받는 메서드
	@GetMapping("/routes")
	public ResponseEntity<?> routesAll() {
		List<RouteListDto> list = null;

		try {
			list = routeService.getTravelRouteAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list != null) {
			return new ResponseEntity<List<RouteListDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204
		}

	}

	// 여행 경로에 대한 상세 정보를 요청하는 메서드
	@GetMapping("/route/{routeNo}")
	public ResponseEntity<?> oneRouteList(@PathVariable String routeNo) {

		RouteDto dto = null;

		try {
			dto = routeService.getTravelRoute(routeNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (dto != null) {
			return new ResponseEntity<RouteDto>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204
		}
	}

	/*
	 * 좋아요 관련 컨트롤러 시작
	 */
	// 좋아요 수 조회
	@GetMapping("route/like/{traceRouteNo}")
	public ResponseEntity<?> countLike(@PathVariable("traceRouteNo") int traceRouteNo) throws Exception {
		int result = routeService.countLike(traceRouteNo);
		if (result >= 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			throw new Exception();
		}
	}

	// 사용자가 해당 핫플레이스에 좋아요를 눌렀는지 체크
	@PostMapping("route/like/check")
	public ResponseEntity<?> checkLike(@RequestBody RouteLike routeLike) throws Exception {
		int result = routeService.checkLike(routeLike);
		if (result >= 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			throw new Exception();
		}
	}

	// 사용자가 핫플레이스를 좋아요 할 때
	@PostMapping("route/like")
	public ResponseEntity<?> routeGood(@RequestBody RouteLike routeLike) throws Exception {
		int result = routeService.routeGood(routeLike);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} else {
			throw new Exception();
		}
	}

	// 사용자가 핫플레이스 좋아요를 취소할 때
	@DeleteMapping("route/like/{travelRouteNo}/{userNo}")
	public ResponseEntity<?> routeGoodCancel(@PathVariable Map<String, String> pathMap) throws Exception {
		
		int travelRouteNo = Integer.parseInt(pathMap.get("travelRouteNo"));
		int userNo = Integer.parseInt(pathMap.get("userNo"));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("travelRouteNo", travelRouteNo);
		map.put("userNo", userNo);
		int result = routeService.routeGoodCancel(map);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			throw new Exception();
		}

	}

}
