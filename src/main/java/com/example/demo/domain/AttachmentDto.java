package com.example.demo.domain;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class AttachmentDto {
	
	private String name;
	private File file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "AttachmentDto [name=" + name + ", file=" + file + "]";
	}

	
}
