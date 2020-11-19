package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.mapper.EnvironmentMapper;
import com.ssafy.happyhouse.model.EnvironmentDto;


@Service
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	EnvironmentMapper environmentMapper;
	@Override
	public List<EnvironmentDto> envirList(String gu) {
		return environmentMapper.envirList(gu);
	}
}
