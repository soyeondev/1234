package com.example.demo.domain;

public class MemberDto {
	
	private int memberKey;
	private String username;
	private String password;
	private String name;
	private String phone;
	private String department;
	private String extNumber;
	private String role;
	private String createAt;
	
	public int getMemberKey() {
		return memberKey;
	}
	public void setMemberKey(int memberKey) {
		this.memberKey = memberKey;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getExtNumber() {
		return extNumber;
	}
	public void setExtNumber(String extNumber) {
		this.extNumber = extNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	
	@Override
	public String toString() {
		return "MemberDto [memberKey=" + memberKey + ", username=" + username + ", password=" + password + ", name="
				+ name + ", phone=" + phone + ", department=" + department + ", extNumber=" + extNumber + ", role="
				+ role + ", createAt=" + createAt + "]";
	}
	
}
