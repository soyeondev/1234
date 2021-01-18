package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.AttachmentDto;
import com.example.demo.service.mapper.FileMapper;

@Service
public class FileService {

	protected static Logger LOGGER = Logger.getLogger(FileService.class.getName());

	@Autowired
	private FileMapper fileMapper;


    @Transactional
	public void uploadFile(AttachmentDto attachmentDto) {
    	String filePath = "C:\\upload";
    	File file = attachmentDto.getFile();
    	//file.getName();
    	System.out.println("file.getName();: "+file.getName());
    	try {
			//attachmentDto.getFile().transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			
		}
    	//fileMapper.createFile(attachmentDto);
	}
}
