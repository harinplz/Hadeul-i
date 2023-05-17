package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.service.TripService;

@RestController

public class TripController {

	private TripService tripService;
	
	public TripController(TripService tripService) {
		this.tripService = tripService;
	}

	//@GetMapping("/trips")
	@PostMapping("/trips")
	public ResponseEntity<?> search(@RequestBody Map<String, String> map) {
		
		String searchArea = map.get("search-area");
		String contentId = map.get("search-content-id");
		String searchKeyWord = map.get("search-keyword");
		String sort = map.get("sort-name");
		
		TripDto dto = new TripDto();
		
		dto.setSido(searchArea);
		dto.setContentId(contentId);
		dto.setSearchKeyWord(searchKeyWord);
		
		List<TripDto> list = null;
		try {
			list = tripService.list(dto, Integer.parseInt(sort));
			
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
	
}
