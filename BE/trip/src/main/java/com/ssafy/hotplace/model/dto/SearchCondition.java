package com.ssafy.hotplace.model.dto;

public class SearchCondition {
	
	private String key="none"; //검색 컬럼명 none은 컬럼이 없는 경우
	private String word; // 검색어
	
	public SearchCondition() {}
	
	//키워드 검색 
	public SearchCondition(String key, String word) {
		super();
		this.key = key;
		this.word = word;
	}

	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "SearchCondition [key=" + key + ", word=" + word + "]";
	}
	
	

}
