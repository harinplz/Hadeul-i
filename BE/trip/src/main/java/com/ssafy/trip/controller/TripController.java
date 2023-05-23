package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
			//list = tripService.list(dto, Integer.parseInt(sort));
			list = tripService.attractionList(dto);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if (list != null) {
			return new ResponseEntity<List<TripDto>>(list, HttpStatus.OK); // 200
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204
		}
	}
	
	@GetMapping("/trips/type/{tableType}")
	public ResponseEntity<?> getEndPageNum(@PathVariable String tableType) {
		
		int count = 0;
		
		try {
			count = tripService.getCount(tableType);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(count != 0) {
			return new ResponseEntity<Integer>(count,HttpStatus.OK );
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
