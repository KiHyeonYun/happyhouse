package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.FreeBoardDto;

public interface FreeBoardService {
	public List<FreeBoardDto> retrieveBoard();
	public FreeBoardDto detailBoard(int no);
	public boolean writeBoard(FreeBoardDto board);
	public boolean updateBoard(FreeBoardDto board);
	public boolean deleteBoard(int no);
	public List<String> retrieveWriter();
}
