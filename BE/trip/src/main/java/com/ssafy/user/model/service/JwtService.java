package com.ssafy.user.model.service;

public interface JwtService {
	
	//토큰 생성 메서드
	<T> String create(String key, T data, String subject, long expiration) throws Exception;
	
	//토큰 유효성 검사 메서드
	boolean checkToken(String jwt);
	
}
