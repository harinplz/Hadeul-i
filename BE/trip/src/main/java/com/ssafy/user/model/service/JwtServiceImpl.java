package com.ssafy.user.model.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {

	@Override
	public <T> String create(String key, T data, String subject, long expiration) throws Exception {
		
		//토큰 생성
		// 토근 생성
				String jwt = Jwts.builder()
						.setHeaderParam("typ", "JWT")
						.setHeaderParam("regDate", System.currentTimeMillis())
						.setExpiration(new Date(System.currentTimeMillis() + expiration))
						.setSubject(subject)
						.claim(key, data)
						.signWith(SignatureAlgorithm.HS256, "hadeuliSecret".getBytes("UTF-8"))
						.compact();
				
				return jwt;

	}

	@Override
	public boolean checkToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("hadeuliSecret".getBytes("UTF-8")).parseClaimsJws(jwt);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
