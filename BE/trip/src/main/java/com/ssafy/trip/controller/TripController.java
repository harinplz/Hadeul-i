package com.ssafy.trip.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.AttractionLike;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchCondition;
import com.ssafy.trip.model.service.TripService;

@RestController

public class TripController {

	private TripService tripService;

	public TripController(TripService tripService) {
		this.tripService = tripService;
	}

	@PostMapping("/trips")
	public ResponseEntity<?> search(@RequestBody Map<String, String> map) {

		String sido = map.get("search-area");
		String category = map.get("search-content-id");
		String searchKeyWord = map.get("search-keyword");
		String tableType = map.get("table-type");

		TripSearchCondition dto = new TripSearchCondition();

		dto.setSido(sido);
		dto.setCategory(category);
		dto.setSearchKeyWord(searchKeyWord);
		dto.setTableType(tableType);

		List<TripDto> list = null;
		try {
			// list = tripService.list(dto, Integer.parseInt(sort));
			list = tripService.attractionList(dto);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (list != null) {
			return new ResponseEntity<List<TripDto>>(list, HttpStatus.OK); // 200
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204
		}
	}

	@GetMapping("/trips/type/{tableType}")
	public ResponseEntity<?> getEndPageNum(@PathVariable String tableType) {

		int count = 0;

		try {
			count = tripService.getCount(tableType);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (count != 0) {
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/trips/{title}")
	public ResponseEntity<?> getSearchResult(@PathVariable String title) {
		List<TripDto> list = null;
		try {
			list = tripService.getSearchResult(title);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (list != null) {
			return new ResponseEntity<List<TripDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/trips/description")
	public ResponseEntity<?> getDescription(@RequestBody AttractionLike dto) throws Exception {
		System.out.println(dto);
		
		String res = null;
		
		try {
			res = tripService.getDescription(dto);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(res != null) {
			return new ResponseEntity<String>(res, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	/*
	 * 좋아요 기능 구현
	 */

	// 좋아요 했는지 체크
	@PostMapping("/trips/like/check")
	public ResponseEntity<?> checkLike(@RequestBody AttractionLike attractionLike) throws Exception {
		System.out.println(attractionLike);
		int result = tripService.checkLike(attractionLike);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	// 사용자가 핫플레이스를 좋아요 할 때
	@PostMapping("/trips/like")
	public ResponseEntity<?> attractionGood(@RequestBody AttractionLike attractionLike) throws Exception {
		int result = tripService.attractionGood(attractionLike);
		
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	// 사용자가 핫플레이스 좋아요를 취소할 때
	@DeleteMapping("trips/like/{attractionType}/{attractionNo}/{userNo}")
	public ResponseEntity<?> attractionGoodCancel(@PathVariable Map<String, String> pathMap) throws Exception {
		
		AttractionLike dto = new AttractionLike();
		dto.setAttractionNo(pathMap.get("attractionNo"));
		dto.setAttractionType(pathMap.get("attractionType"));
		dto.setUserNo(Integer.parseInt(pathMap.get("userNo")));
		
		int result = tripService.attractionGoodCancel(dto);
		
		if (result > 0) {
			result = tripService.countLike(dto);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	// 좋아요 수 조회
	@GetMapping("trips/like/{attractionType}/{attractionNo}")
	public ResponseEntity<?> countLike(@PathVariable("attractionNo") String attractionNo, 
										@PathVariable("attractionType") String attractionType) throws Exception {
		
		AttractionLike dto = new AttractionLike();
		dto.setAttractionNo(attractionNo);
		dto.setAttractionType(attractionType);
		
		int result = tripService.countLike(dto);		
		
		if (result >= 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
