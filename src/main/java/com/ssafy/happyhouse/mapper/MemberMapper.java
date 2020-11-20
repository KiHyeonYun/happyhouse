package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public int update(MemberDto memberDto);
	public int delete(String id);
	public MemberDto read(String userid);
	public int regist(MemberDto memberDto);
}
