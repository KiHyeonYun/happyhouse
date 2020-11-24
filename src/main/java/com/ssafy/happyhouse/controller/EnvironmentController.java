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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.EnvironmentDto;
import com.ssafy.happyhouse.service.EnvironmentService;
import io.swagger.annotations.ApiOperation;

//http://localhost:8888/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/envir")
public class EnvironmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(EnvironmentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private EnvironmentService environmentService;
	
	 @ApiOperation(value = "모든 환경정보의 정보를 반환한다.", response = List.class)
		@GetMapping(value = "/envirList/")
		public ResponseEntity<List<EnvironmentDto>> envirList(Map<String, String> map) throws Exception {
			logger.debug("envirList - 호출  : "+ map);
			return new ResponseEntity<List<EnvironmentDto>>(environmentService.envirList(map), HttpStatus.OK);
		}
}
