package com.ssafy.hotplace.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.hotplace.model.dto.HotplaceDto;

public interface HotplaceService {
	
	public int insert(HotplaceDto hotplace) throws SQLException;
	
	public ArrayList<HotplaceDto> selectAll() throws SQLException;
	
	

}
