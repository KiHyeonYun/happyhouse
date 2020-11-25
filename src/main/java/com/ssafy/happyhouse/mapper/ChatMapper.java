package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.ChatDto;

@Mapper
public interface ChatMapper {

	boolean chatWrite(ChatDto chat)throws Exception;
	List<ChatDto> chatList(String userid)throws Exception;
}
