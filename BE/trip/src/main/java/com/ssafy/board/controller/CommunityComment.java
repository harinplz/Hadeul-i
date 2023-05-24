package com.ssafy.board.controller;

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

import com.ssafy.board.model.dto.CommentDto;
import com.ssafy.board.model.service.CommentService;

@RestController
@RequestMapping("/community/comment")
@CrossOrigin("*")
public class CommunityComment {

	CommentService service;
	
	@Autowired
	public CommunityComment(CommentService serivce) {
		this.service = serivce;
	}
	
	@GetMapping("/{communityNo}")
	public ResponseEntity<?> getComments(@PathVariable("communityNo") int boardNo){
		
		try {
			List<CommentDto> comments = service.getComments(boardNo);
			return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> insertComment(@RequestBody CommentDto dto){
		System.out.println("post 요");
		try {
			List<CommentDto> comments = service.postComment(dto);
			return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@PutMapping
	public ResponseEntity<?> putComment(@RequestBody CommentDto dto){
		System.out.println("put 요청");
		try {
			List<CommentDto> comments = service.updateComment(dto);
			return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}	
	}
	
	@DeleteMapping("/{communityNo}/{commentNo}")
	public ResponseEntity<?> deleteComment(@PathVariable("communityNo") int boardNo, @PathVariable int commentNo){
		System.out.println("삭제 요청 " + boardNo + ", " + commentNo);
		try {
			List<CommentDto> comments = service.delComment(boardNo, commentNo);
			return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}	
	}
	
}
