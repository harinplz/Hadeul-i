package com.ssafy.hotplace.model.service;

import java.sql.SQLException;

import com.ssafy.hotplace.model.dto.HotplaceDto;

public interface HotplaceService {
	
	public int insert(HotplaceDto hotplace) throws SQLException;
	
	

}
