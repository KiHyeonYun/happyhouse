package com.ssafy.happyhouse.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {
	

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String getDongCode(String dong) throws Exception {
		return sqlSession.getMapper(MapMapper.class).getDongCode(dong);
	}
}
