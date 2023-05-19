package com.ssafy.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.user.model.dto.User;
import com.ssafy.user.model.service.JwtService;
import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController{

	private UserService userService;
	private JwtService jwtService;

	public UserController(UserService userService, JwtService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}



	//회원가입 구현 (일단 암호화 복호화 구현 X) - C
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody User user) throws Exception {
		int result = userService.insert(user);
		if(result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
		}
		else {
			throw new Exception();
		}
	}

	//로그인 구현 (...)
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws Exception {
		User loginUser = userService.select(user);
		if(loginUser!=null) {
			// 세션 스토리지에 저장하는 느낌으로 구현하기... 
			// AccessToken 생성
			String accessToken = jwtService.create("userid", loginUser.getId(), "access-token", 1000 * 10 /*10초 추후 수정*/);

			// RefreshToken 생성
			String refreshToken = jwtService.create("userid",
					loginUser.getId(),
					"refresh-token", 
					1000 * 30 /* 30초 추후 수정 */);

			// refreshToken을 DB에 저장
			userService.updateTokenByUserId(loginUser.getId(), refreshToken);


			// 토큰을 클라이언트로 응답하기
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("access-token", accessToken);
			resultMap.put("refresh-token", refreshToken);

			return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);

		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping("/info/{id}")
	public ResponseEntity<?> getInfo(@PathVariable String id, HttpServletRequest request) throws SQLException {
		
		String accessToken = request.getHeader("access-token");
		
		// 토큰에 이상이 없다면
		if (jwtService.checkToken(accessToken)) {
			
			// 회원 정보 찾기
			User user = userService.selectId(id);
			
			if (user != null) {
				// 회원 존재하면 응답
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("userInfo", user);
				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
			else {
				// 존재하지 않으면 401
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		}
		else {
			// 토큰에 이상이 있다면 401
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@PostMapping("/refresh")
public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) throws Exception {
		
		refreshToken = refreshToken.substring(0, refreshToken.length() - 1);
		
		// 사용 가능한 refreshToken 이면
		if (jwtService.checkToken(refreshToken)) {
			
			// refreshToken은 중복되지 않은 유일한 값이므로
			// refreshToken을 이용하여 사용자 정보 얻기
			User user = userService.selectByToken(refreshToken);
			
			// 로그인 된 경우
			if (user != null) {
				// AccessToken 생성
				String newAccessToken = jwtService.create("userid",
						user.getId(),
						"access-token", 
						1000 * 10 /* 10초 추후 수정*/);

				// RefreshToken 생성
				String newRefreshToken = jwtService.create("userid",
						user.getId(),
						"refresh-token", 
						1000 * 30 /* 30초  추후 수정*/);
				
				// refreshToken을 DB에 저장
				userService.updateTokenByUserId(user.getId(), refreshToken);
				
				// 토큰을 클라이언트로 응답하기
				Map<String, String> resultMap = new HashMap<>();
				resultMap.put("access-token", newAccessToken);
				resultMap.put("refresh-token", newRefreshToken);

				return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
			}
			else {
				// 로그인 실패 시
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);  // 401
			}
		}
		else {
			// 기간 만료된 refreshToken일 경우
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);  // 401
		}
		
	}




	// 마이페이지 정보 조회
	@GetMapping("/mypage/{userId}")
	public ResponseEntity<?> userInfo(@PathVariable("userId") String userId) throws Exception {
		User user = userService.selectId(userId);
		if(user!=null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}


	// 마이페이지 정보 수정
	@PutMapping("/mypage/{userId}")
	public ResponseEntity<?> userModify(@RequestBody User user, @PathVariable("userId") String id) throws Exception {
		int result = userService.update(user);
		if(result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else {
			throw new Exception();
		}
	}


	// 마이페이지에서 탈퇴 
	@DeleteMapping("/mypage/{userId}")
	public ResponseEntity<?> userDelete(@PathVariable("userId") String userId) throws Exception {
		int result = userService.delete(userId);
		if(result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else {
			throw new Exception();
		}
	}

}
