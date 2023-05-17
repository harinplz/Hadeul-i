package com.ssafy.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        super();
        this.userService = userService;
    }
    
    //관리자가 회원 가입시키기 C
    @PostMapping("/list")
    public ResponseEntity<?> userRegister(@RequestBody User user) throws Exception {
        int result = userService.insert(user);
        if(result > 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
        }
        else {
            throw new Exception();
        }
    }

    //모든 회원 정보 조회 R
    @GetMapping("/list")
    public ResponseEntity<?> searchAll() throws Exception {
        List<User> list = userService.searchAll();
        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<List<User>>(list, HttpStatus.OK); //200
        }
        else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); //204
        }
    }

    //회원 정보 하나씩 조회 R (관리자가 조회)
    @GetMapping("/list/{userId}")
    public ResponseEntity<?> selectUser(@PathVariable("userId") String userId) throws Exception {
        User user = userService.selectId(userId);
        if(user!=null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
    
  //회원 정보 수정 U
    // 관리자가 수정 
    @PutMapping("/list/{userNo}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("userNo") int no) throws Exception {
        int result = userService.update(user);
        if(result > 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
        else {
            throw new Exception();
        }
    }
    
    //관리자가 회원 삭제 시킴 D
    @DeleteMapping("/list/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) throws Exception {
        
    	int result = userService.delete(userId);
        if(result > 0) {
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        }
        else {
            throw new Exception();
        }
    }

}
