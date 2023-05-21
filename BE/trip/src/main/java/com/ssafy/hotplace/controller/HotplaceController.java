package com.ssafy.hotplace.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.System.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.hotplace.model.dto.HotplaceDto;
import com.ssafy.hotplace.model.service.HotplaceService;

import jdk.internal.org.jline.utils.Log;

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
		
//		hotplaceDto.setBlobImg(imageToByteArray(hotplaceDto.getImg()));
		
		int result = hotplaceService.insert(hotplaceDto);
		if(result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		else {
			throw new Exception();
		}		
	}	
	
//	public static byte[] imageToByteArray(MultipartFile file) throws Exception {
//		byte[] returnValue = null;
//		
//		ByteArrayOutputStream baos = null;
//		FileInputStream fis = null;
//		
//		try {
//			baos = new ByteArrayOutputStream();
//			
//			byte[] buf = new byte[1024];
//			int read = 0;
//			
//			while((read=file) != -1) {
//				baos.write(buf, 0, read);
//			}
//			
//			returnValue = baos.toByteArray();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			if(baos != null) baos.close();
//			if(fis != null) baos.close();
//		}
//		
//		return returnValue;
//	}
}
