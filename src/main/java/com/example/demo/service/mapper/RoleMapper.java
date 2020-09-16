package com.example.demo.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.RoleDto;

@Mapper
public interface RoleMapper {
	@Insert("INSERT into  tb_role (username, role) VALUES (#{username}, #{role})")
	public Long insertRole(RoleDto roleDto);
}
