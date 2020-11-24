package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CoronaDto;
import com.ssafy.happyhouse.model.HouseDto;
import com.ssafy.happyhouse.service.MapService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8888/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(EnvironmentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MapService mapService;
	
	 @ApiOperation(value = "dongcode를 반환한다.", response = List.class)
		@GetMapping(value = "/map/{search}")
		public ResponseEntity<String> envirList(@PathVariable("search") String search) throws Exception {
			logger.debug("getdongcode - 호출  : "+ search);
			return new ResponseEntity<String>(mapService.getDongCode(search),HttpStatus.OK);
		}
	 
	 @ApiOperation(value = "detailHouse를 반환한다.", response = List.class)
		@GetMapping(value = "/map/detail/{no}")
		public ResponseEntity<HouseDto> detailhouse(@PathVariable("no") int no) throws Exception {
			logger.debug("하우스 정보 - 호출  : "+ no);
			return new ResponseEntity<HouseDto>(mapService.getHouseDetail(no),HttpStatus.OK);
		}
	 
	 @ApiOperation(value = "house list를 반환한다.", response = List.class)
		@GetMapping(value = "/map/searchHouse/")
		public ResponseEntity<List<HouseDto>> houseList(@RequestParam Map<String, Object> map) throws Exception {
			logger.debug("하우스 리스트 - 호출  : "+ map);
			
			return new ResponseEntity<List<HouseDto>>(mapService.getHouseList(map),HttpStatus.OK);
		}
	 @ApiOperation(value = "코로나 병원 list를 반환한다.", response = List.class)
		@GetMapping(value = "/map/corona/")
		public ResponseEntity<List<CoronaDto>> coronaHCList(@RequestParam Map<String, String> map) throws Exception {
			logger.debug("병원 또는 클리닉 - 호출  : "+ map);
			
			return new ResponseEntity<List<CoronaDto>>(mapService.getCoronaList(map),HttpStatus.OK);
		}
	//select 
	 
}
