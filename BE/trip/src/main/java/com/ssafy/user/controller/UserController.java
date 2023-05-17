package com.ssafy.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController{

	UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
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
			return new ResponseEntity<User>(loginUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
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
