package com.ssafy.hotplace.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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


	@Override
	public int addToUserAttraction(HotplaceDto hotplaceDto) throws SQLException {
		Map<String, Integer> sidoMap = new HashMap<String, Integer>();
		sidoMap.put("서울", 1);
		sidoMap.put("인천", 2);
		sidoMap.put("대전", 3);
		sidoMap.put("대구", 4);
		sidoMap.put("광주", 5);
		sidoMap.put("부산", 6);
		sidoMap.put("울산", 7);
		sidoMap.put("세종", 8);
		sidoMap.put("경기", 31);
		sidoMap.put("강원", 32);
		sidoMap.put("충북", 33);
		sidoMap.put("충남", 34);
		sidoMap.put("경북", 35);
		sidoMap.put("경남", 36);
		sidoMap.put("전북", 37);
		sidoMap.put("전남", 38);
		sidoMap.put("제주", 39);
		
		String key = hotplaceDto.getJibun().substring(0, 2);
		hotplaceDto.setSido(sidoMap.get(key));
		
		return hotplaceMapper.addToUserAttraction(hotplaceDto);
	}
	
	

}
