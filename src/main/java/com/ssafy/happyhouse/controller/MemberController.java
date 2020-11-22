package com.ssafy.happyhouse.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.help.BooleanResult;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.MemberService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MemberService memberService;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@ApiOperation(value = "로그인 확인", response = Map.class)
	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto member, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(member);
		try {
			MemberDto loginMember = memberService.login(member);
			if(loginMember != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginMember);
				logger.trace("로그인 토큰정보 : {}", token);
				
//				토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//				response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginMember.getUserid());
				resultMap.put("user-name", loginMember.getUsername());
//				resultMap.put("status", true);
//				resultMap.put("data", loginMember);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패! 아이디와 비밀번호를 확인하세요.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	 @ApiOperation(value = "로그인된 개인 정보를 조회한다", response = MemberDto.class)
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
			String info = memberService.getServerInfo();
			
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//
			resultMap.put("status", true);
			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	 @ApiOperation(value="회원등록을 합니다.",response=BooleanResult.class)
	 @PostMapping("/regist")
	 public ResponseEntity<BooleanResult> registMember(@RequestBody MemberDto dto) throws Exception{
		 logger.info("회원등록"+new Date());
		 logger.info("회원등록"+dto);
		 boolean checkRegi = memberService.regist(dto);
		 BooleanResult br=new BooleanResult();
		 br.setCheck(checkRegi);
		 br.setName("regist");
		 br.setState("success");
		 logger.info("회원등록 checkRegi "+checkRegi);
		 if(!checkRegi) {
			 br.setCheck(false);
			 br.setName("regist");
			 br.setState("fail");
		return new ResponseEntity<BooleanResult>(br,HttpStatus.OK);
		 }
		return new ResponseEntity<BooleanResult>(br,HttpStatus.OK);
		 
	 }
	 @ApiOperation(value="회원의 정보를 업데이트합니다. 정보가 변경되면 자동(trigger)로 추가됩니다. ",response=BooleanResult.class)
	 @PutMapping("/update")
	 public ResponseEntity<BooleanResult> updateMember(@RequestBody MemberDto dto) throws Exception{
		 logger.info("회원 정보 수정 "+new Date());
		 logger.info("회원 정보 수정" +dto);
		 boolean checkUpdate=memberService.update(dto);
		 BooleanResult br=new BooleanResult();
		 br.setCheck(checkUpdate);
		 br.setName("update");
		 br.setState("success");
		 if(!checkUpdate) {
			 br.setCheck(false);
			 br.setName("regist");
			 br.setState("fail");
			 
			 return new ResponseEntity<BooleanResult>(HttpStatus.NO_CONTENT);
		 }
		return new ResponseEntity<BooleanResult>(br,HttpStatus.OK);
		 
	 }
	 @ApiOperation(value="회원의 정보를 삭제합니다 (탈퇴)" ,response=BooleanResult.class)
	@DeleteMapping("/delete")
	 public ResponseEntity<BooleanResult>deleteMember(@RequestBody String userid) throws Exception{
		 logger.info("회원 정보 삭제 !!!!"+new Date());
		 logger.info("회원 정보 삭제 !!!!"+userid);
		 boolean checkdelete=memberService.delete(userid);
		 BooleanResult br=new BooleanResult();
		 br.setCheck(checkdelete);
		 br.setName("delete");
		 br.setState("success");
		 return new ResponseEntity<BooleanResult>(br,HttpStatus.OK);
	 }
}
