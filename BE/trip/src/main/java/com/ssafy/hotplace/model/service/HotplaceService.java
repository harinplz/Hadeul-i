package com.ssafy.hotplace.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.hotplace.model.dto.HotplaceDto;
import com.ssafy.hotplace.model.dto.SearchCondition;

public interface HotplaceService {
	
	public int insert(HotplaceDto hotplace) throws SQLException;
	
	public ArrayList<HotplaceDto> selectAll() throws SQLException;

	public ArrayList<HotplaceDto> selectKeyword(SearchCondition searchCondition) throws SQLException;
	
	

}
