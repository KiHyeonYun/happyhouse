package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public boolean regist(MemberDto memberDto) throws Exception;
	public MemberDto read(String id) throws Exception;
	public boolean update(MemberDto memberDto) throws Exception;
	public boolean delete(String userid) throws Exception;
	public String getServerInfo();
	
}
