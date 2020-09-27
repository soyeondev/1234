package com.example.demo.domain;

public class MemberLogDto {

	private int memberLogKey;
	private String username;
	private String name;
	private String address;
	private String macAddress;
	private String createAt;
	
	public int getMemberLogKey() {
		return memberLogKey;
	}
	public void setMemberLogKey(int memberLogKey) {
		this.memberLogKey = memberLogKey;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	@Override
	public String toString() {
		return "MemberLogDto [memberLogKey=" + memberLogKey + ", username=" + username + ", name=" + name + ", address="
				+ address + ", macAddress=" + macAddress + ", createAt=" + createAt + "]";
	}
}
