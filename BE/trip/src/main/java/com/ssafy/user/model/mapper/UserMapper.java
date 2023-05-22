package com.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.user.model.dto.User;

@Mapper
public interface UserMapper {
	
	public int insert(User user) throws SQLException;
	
	public User select(User user) throws SQLException;
	
	public User selectId(String id) throws SQLException;
	
	public User selectPwd(String id, String name) throws SQLException;
	
	public int delete(String id) throws SQLException;
	
	public int update(User user) throws SQLException;

	public ArrayList<User> searchAll() throws SQLException;

	public int updateTokenByUserId(Map<String, String> params);

	public User selectByToken(String refreshToken);

	public User selectOne(int userNo) throws SQLException;


}
