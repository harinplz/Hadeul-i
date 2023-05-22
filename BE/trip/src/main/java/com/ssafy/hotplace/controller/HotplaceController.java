package com.ssafy.hotplace.controller;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.hotplace.model.dto.HotplaceDto;
import com.ssafy.hotplace.model.service.HotplaceService;


@RestController
@RequestMapping("/hotplace")
@CrossOrigin("*")
public class HotplaceController {


	private ResourceLoader resourceLoader;	
	private HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(ResourceLoader resourceLoader, HotplaceService hotplaceService) {
		super();
		this.resourceLoader = resourceLoader;
		this.hotplaceService = hotplaceService;
	}


	@PostMapping
	public ResponseEntity<?> insert(HotplaceDto hotplaceDto, MultipartFile upfile) throws Exception {
		System.out.println(upfile);
		// 클라이언트로 부터 전달받은 파일이 존재하면 아래와 같이 처리
		if (upfile != null && upfile.getSize() > 0) {

			// 파일을 저장할 폴더 지정
			Resource resource = resourceLoader.getResource("resources" + File.separator + "upload");
			String realPath = resource.getFile().getCanonicalPath();

			// 파일을 저장할 폴더가 존재하지 않으면 폴더 생성하기
			if (!resource.getFile().exists()) {
				new File(realPath).mkdirs();
			}

			// 서버에 저장할 파일 이름을 생성
			String img = System.currentTimeMillis() + "_" + upfile.getOriginalFilename();

			// 실제 파일이름(사용자가 올린 파일이름)
			String orgImg = upfile.getOriginalFilename();

			hotplaceDto.setImg(img);
			hotplaceDto.setOrgImg(orgImg);

			upfile.transferTo(new File(realPath + File.separator + hotplaceDto.getImg()));
		}


		int result = hotplaceService.insert(hotplaceDto);
		if(result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		else {
			throw new Exception();
		}		
	}
	
	
	//  핫플레이스 목록 (전체 조회)
	@GetMapping
	public ResponseEntity<?> selectAll() throws Exception {
		List<HotplaceDto> list = hotplaceService.selectAll();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HotplaceDto>>(list, HttpStatus.OK);
		}
		else {
			 return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); //204 
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
