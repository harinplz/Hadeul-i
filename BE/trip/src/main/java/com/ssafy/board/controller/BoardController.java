package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/list")
	public ResponseEntity<?> searchAll() throws Exception{
		List<BoardDto> list = boardService.getList();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getBoardNo());
		}
		
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
}
