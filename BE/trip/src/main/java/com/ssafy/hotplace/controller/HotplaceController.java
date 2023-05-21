package com.ssafy.hotplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hotplace.model.dto.HotplaceDto;
import com.ssafy.hotplace.model.service.HotplaceService;

@RestController
@RequestMapping("/hotplace")
@CrossOrigin("*")
public class HotplaceController {
	
	HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}
	
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody HotplaceDto hotplaceDto) throws Exception {
		int result = hotplaceService.insert(hotplaceDto);
		if(result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		else {
			throw new Exception();
		}		
	}	
}
