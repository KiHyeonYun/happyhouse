package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.CoronaDto;
import com.ssafy.happyhouse.model.HouseDto;

@Mapper
public interface MapMapper {
	public String getDongCode(String dong) throws Exception;
	public HouseDto getHouseDetail(int no) throws Exception;
	public List<HouseDto> getHouseListDong(String keyword) throws Exception;
	public List<HouseDto> getHouseListName(String keyword) throws Exception;
	public List<CoronaDto> getCoronaList(Map<String, String> map) throws Exception;
	
}
