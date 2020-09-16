package com.example.demo.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.MemberDto;

@Mapper
public interface MemberMapper {
	@Select("SELECT * from tb_member WHERE username=#{username}")
	public MemberDto findOneById(String username);
	
	@Insert("INSERT into tb_member (username, password, name, roll) VALUES (#{username}, #{password}, #{name}, #{roll})")
	public Long insertMember(MemberDto memberDto);

}
