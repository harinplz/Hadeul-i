package com.ssafy.board.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.service.BoardService;

@RestController
@RequestMapping("/community")
@CrossOrigin("*")
public class BoardController {
	
	BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService =boardService;
	}
	
	@GetMapping("/list") //게시글 목록 요청을 받는 메서드입니다.
	public ResponseEntity<?> searchAll() throws Exception{
		List<BoardDto> list = boardService.getList();
		
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{boardNo}") //게시글 상세 요청을 받는 메서드입니다.
	public ResponseEntity<?> detailBoard(@PathVariable("boardNo") int boardNo) throws Exception{
		BoardDto dto = boardService.getOne(boardNo);
		
		if(dto!= null) {
			return new ResponseEntity<BoardDto>(dto, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{boardNo}") //게시글 삭제 요청을 받는 메서드입니다.
	public ResponseEntity<?> deleteOne(@PathVariable("boardNo") int boardNo) throws Exception{
		
		if(boardService.deleteOne(boardNo)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping  //게시글 수정  요청을 받는 메서드입니다.
	public ResponseEntity<?> updateOne(@RequestBody BoardDto boardDto) throws Exception{
		
		if(boardService.updateOne(boardDto)) {			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> insertOne(@RequestBody BoardDto boardDto) throws Exception{
		if(boardService.insertOne(boardDto)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
