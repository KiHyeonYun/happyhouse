package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.EnvironmentDto;

@Mapper
public interface EnvironmentMapper {
	public List<EnvironmentDto> envirList(String gu);
}
