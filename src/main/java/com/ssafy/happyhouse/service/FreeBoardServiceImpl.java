package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.mapper.FreeBoardMapper;
import com.ssafy.happyhouse.model.FreeBoardDto;


@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
    @Autowired
	private FreeBoardMapper freeBoardMapper;

    @Override
	public List<FreeBoardDto> retrieveBoard() {
		return freeBoardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(FreeBoardDto board) {
		return freeBoardMapper.insertBoard(board) == 1;
	}

	@Override
	public List<String> retrieveWriter() {
		return freeBoardMapper.writerList();
	}

	@Override
	public FreeBoardDto detailBoard(int no) {
		return freeBoardMapper.selectBoardByNo(no);
	}

	@Override
	@Transactional
	public boolean updateBoard(FreeBoardDto board) {
		return freeBoardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int no) {
		if( freeBoardMapper.deleteBoard(no) == 1) {
			//freeBoardMapper.reSortNo();
			return true;
		}
		return false;
	}
}