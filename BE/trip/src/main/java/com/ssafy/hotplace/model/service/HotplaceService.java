package com.ssafy.hotplace.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.hotplace.model.dto.HotplaceDto;
import com.ssafy.hotplace.model.dto.HotplaceLike;
import com.ssafy.hotplace.model.dto.SearchCondition;

public interface HotplaceService {
	
	public int insert(HotplaceDto hotplace) throws SQLException;
	
	public ArrayList<HotplaceDto> selectAll() throws SQLException;

	public ArrayList<HotplaceDto> selectKeyword(SearchCondition searchCondition) throws SQLException;

	public HotplaceDto selectOne(int hotplaceNo) throws SQLException;

	public int delete(int hotplaceNo) throws SQLException;

	public int countLike(int hotplaceNo) throws SQLException;

	public int checkLike(HotplaceLike hotplaceLike) throws SQLException;

	public int hotplaceGood(HotplaceLike hotplaceLike) throws SQLException;

	public int hotplaceGoodCancel(Map<String, Integer> map) throws SQLException;

	public int addToUserAttraction(HotplaceDto hotplaceDto) throws SQLException;
	
	

}
