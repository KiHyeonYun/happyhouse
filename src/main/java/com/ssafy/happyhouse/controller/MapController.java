package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
