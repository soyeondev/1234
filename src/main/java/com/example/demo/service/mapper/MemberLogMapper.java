package com.example.demo.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.MemberLogDto;

@Mapper
public interface MemberLogMapper {
	@Insert("INSERT into member_log (username, name, address, mac_address, create_at) VALUES (#{username}, #{name}, #{address}, #{macAddress}, NOW())")
	public Long createLog(MemberLogDto memberLogDto);
	
	@Select("SELECT MAX(create_at) from member_log where username=#{username}")
	 public String getLog(String username);

}
