package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.mapper.MapMapper;
import com.ssafy.happyhouse.model.CommDto;
import com.ssafy.happyhouse.model.CoronaDto;
import com.ssafy.happyhouse.model.HouseDto;

@Service
public class MapServiceImpl implements MapService {
	

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String getDongCode(String dong) throws Exception {
		return sqlSession.getMapper(MapMapper.class).getDongCode(dong);
	}

	@Override
	public HouseDto getHouseDetail(int no) throws Exception {
		return sqlSession.getMapper(MapMapper.class).getHouseDetail(no);
	}

	@Override
	public List<HouseDto> getHouseList(Map<String, Object> map) throws Exception {
		if(map.get("searchType").toString().equals("0")) {
			return sqlSession.getMapper(MapMapper.class).getHouseListDong(map.get("keyword").toString());
		} 
		else {
			return sqlSession.getMapper(MapMapper.class).getHouseListName(map.get("keyword").toString());
		}
	}

	@Override
	public List<CoronaDto> getCoronaList(Map<String, String> map) throws Exception {  //getCoronaList
		return sqlSession.getMapper(MapMapper.class).getCoronaList(map);
	}

	@Override
	public List<CommDto> getCommList(String dong) throws Exception {
		
		return sqlSession.getMapper(MapMapper.class).getCommList(dong);
	}
	
	
}
