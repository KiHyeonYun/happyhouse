package com.ssafy.happyhouse.service;

import java.util.List;
import com.ssafy.happyhouse.model.ChatDto;

public interface ChatService {
	boolean chatWrite(ChatDto chat)throws Exception;
	List<ChatDto> chatList(String userid)throws Exception;
	
}
