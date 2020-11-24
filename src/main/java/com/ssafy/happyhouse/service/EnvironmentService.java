package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.EnvironmentDto;

public interface EnvironmentService {
	public List<EnvironmentDto> envirList(Map<String,String> map);
}
