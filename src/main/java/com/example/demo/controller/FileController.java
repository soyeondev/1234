package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.AttachmentDto;
import com.example.demo.service.FileService;

@RestController
public class FileController {
	   private static final Logger LOGGER = Logger.getLogger(FileController.class);
	
	   @Autowired
	   private FileService fileService;

	    // 메인 페이지
//	    @RequestMapping(value = "/attachment", method = RequestMethod.POST)
	    @PostMapping("/attachment")
	    public String attachment(AttachmentDto attachemntDto) {
	    	System.out.println(attachemntDto.toString());
	    	System.out.println("controller in");
	    	fileService.uploadFile(attachemntDto);
	        return "index";
	    }

}
