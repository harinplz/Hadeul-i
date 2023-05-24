package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.user.model.dto.MyPageDto;
import com.ssafy.user.model.dto.User;


public interface UserService {
	
	public int insert(User user) throws SQLException;
	
	public User select(User user) throws SQLException;
	
	public User selectId(String id) throws SQLException;
	
	public User selectPwd(String id, String name) throws SQLException;
	
	public int delete(String id) throws SQLException;
	
	public int update(User user) throws SQLException;

	public ArrayList<User> searchAll() throws SQLException;

	public void updateTokenByUserId(String id, String refreshToken);

	public User selectByToken(String refreshToken);

	public User selectOne(int userNo) throws SQLException;

	public MyPageDto onload(String userNo) throws SQLException;

}
