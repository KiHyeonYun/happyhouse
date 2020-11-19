package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.FreeBoardDto;
@Mapper
public interface FreeBoardMapper {
	public List<FreeBoardDto> selectBoard();
	public FreeBoardDto selectBoardByNo(int no);
	public int insertBoard(FreeBoardDto board);
	public int updateBoard(FreeBoardDto board);
	public int deleteBoard(int no);
	public List<String> writerList();
	public boolean writerRegister(Map<String, String> user);
	public void reSortNo();
}