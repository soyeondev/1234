package com.example.demo.domain;

public class UserLogDto {
	private int idx;
	private String username;
	private String name;
	private String createAt;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	
	
	@Override
	public String toString() {
		return "UserLogDto [idx=" + idx + ", username=" + username + ", name=" + name + ", createAt=" + createAt + "]";
	}
	
	
}
