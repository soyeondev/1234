package com.example.demo.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.AttachmentDto;


@Mapper
public interface FileMapper {

	@Insert("INSERT into attachment (name, create_at) VALUES (#{name}, NOW())")
	public Long createFile(AttachmentDto fileDto);

}
