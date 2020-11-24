package com.ssafy.happyhouse.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MapMapper {
	public String getDongCode(String dong) throws Exception;
}
