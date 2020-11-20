package com.ssafy.happyhouse.service;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.mapper.MemberMapper;
import com.ssafy.happyhouse.model.MemberDto;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public String getServerInfo() {
		return "사용자에게 전달하고 싶은 중요정보";
	}

	@Override
	public boolean regist(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).regist(memberDto) == 1;
	}

	@Override
	public MemberDto read(String id) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).read(id);
	}

	@Override
	public boolean update(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).update(memberDto) == 1;
	}

	@Override
	public boolean delete(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).delete(userid) == 1;
	}



}
