package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CommDto;
import com.ssafy.happyhouse.model.CoronaDto;
import com.ssafy.happyhouse.model.HouseDto;

public interface MapService {
	public String getDongCode(String dong) throws Exception;
	public HouseDto getHouseDetail(int no)throws Exception;
	public List<HouseDto> getHouseList(Map<String, Object> map)throws Exception;
	public List<CoronaDto> getCoronaList(Map<String, String> map)throws Exception;
	public List<CommDto> getCommList(String dong)throws Exception;
}
