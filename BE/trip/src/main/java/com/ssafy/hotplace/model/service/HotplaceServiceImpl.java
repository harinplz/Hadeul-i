package com.ssafy.hotplace.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hotplace.model.dto.HotplaceDto;
import com.ssafy.hotplace.model.dto.HotplaceLike;
import com.ssafy.hotplace.model.dto.SearchCondition;
import com.ssafy.hotplace.model.mapper.HotplaceMapper;

@Service
public class HotplaceServiceImpl implements HotplaceService{
	
	private HotplaceMapper hotplaceMapper;
	
	@Autowired
	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		super();
		this.hotplaceMapper = hotplaceMapper;
	}


	@Override
	public int insert(HotplaceDto hotplace) throws SQLException {
		return hotplaceMapper.insert(hotplace);
	}


	@Override
	public ArrayList<HotplaceDto> selectAll() throws SQLException {
		return hotplaceMapper.selectAll();
	}


	@Override
	public ArrayList<HotplaceDto> selectKeyword(SearchCondition searchCondition) throws SQLException {
		return hotplaceMapper.selectKeyword(searchCondition);
	}


	@Override
	public HotplaceDto selectOne(int hotplaceNo) throws SQLException {
		return hotplaceMapper.selectOne(hotplaceNo);
	}


	@Override
	public int delete(int hotplaceNo) throws SQLException {
		return hotplaceMapper.delete(hotplaceNo);
	}


	@Override
	public int countLike(int hotplaceNo) throws SQLException {
		return hotplaceMapper.countLike(hotplaceNo);
	}


	@Override
	public int checkLike(HotplaceLike hotplaceLike) throws SQLException {
		return hotplaceMapper.checkLike(hotplaceLike);
	}


	@Override
	public int hotplaceGood(HotplaceLike hotplaceLike) throws SQLException {
		return hotplaceMapper.hotplaceGood(hotplaceLike);
	}


	@Override
	public int hotplaceGoodCancel(Map<String, Integer> map) throws SQLException {
		return hotplaceMapper.hotplaceGoodCancel(map);
	}
	
	

}
