package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.EnvironmentDto;

@Mapper
public interface EnvironmentMapper {
	public List<EnvironmentDto> envirList(Map<String, String> map);
}
