package com.example.demo.service.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.UserLogDto;

@Mapper
public interface UserLogMapper {
	@Insert("INSERT into user_log (username, name, address, mac_address, create_at) VALUES (#{username}, #{name}, #{address}, #{macAddress}, NOW())")
	public Long createLog(UserLogDto userLogDto);
	
	@Select("SELECT MAX(create_at) from user_log where username=#{username}")
	public String getLog(String username);

}
