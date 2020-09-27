package com.example.demo.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.domain.MemberDto;

@Mapper
public interface MemberMapper {
	
	@Insert("INSERT into member (username, password, name, phone, department, ext_number, create_at) VALUES (#{username}, #{password}, #{name}, #{phone}, #{department}, #{extNumber}, NOW())")
	public Long insertMember(MemberDto memberDto);

	@Select("SELECT * from member WHERE username=#{username}")
	public MemberDto findOneById(String username);

	@Select("SELECT * from member")
	public List<MemberDto> memberList();

	@Update("UPDATE member set name=#{name}, phone=#{phone}, department=#{department}, ext_number=#{extNumber}"
			+ "WHERE member_key=#{memberKey}")
	public void memberUpdate(MemberDto memberDto);
}
