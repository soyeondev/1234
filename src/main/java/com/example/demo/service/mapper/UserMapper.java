package com.example.demo.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.UserDto;


@Mapper
public interface UserMapper {
	@Select("SELECT * from user WHERE username=#{username}")
	public UserDto findOneById(String username);
	
	@Insert("INSERT into user (username, password, name) VALUES (#{username}, #{password}, #{name})")
	public Long insertUser(UserDto userDto);

}
