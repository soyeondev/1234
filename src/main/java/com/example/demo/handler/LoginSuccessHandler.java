package com.example.demo.handler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.domain.MemberDto;
import com.example.demo.domain.MemberLogDto;
import com.example.demo.service.mapper.MemberLogMapper;
import com.example.demo.service.mapper.MemberMapper;
import com.example.demo.service.mapper.UserLogMapper;
import com.example.demo.service.mapper.UserMapper;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	protected static Logger LOGGER = Logger.getLogger(LoginSuccessHandler.class.getName());
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberLogMapper memberLogMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LOGGER.info("onAuthenticationSuccess in");
		
		String macIp = "";
		InetAddress ip;
		
		try {
			ip = InetAddress.getLocalHost();
		   
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
		   
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			macIp = sb.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e){
			e.printStackTrace();
		}
		
		String addr = request.getRemoteAddr();
		
		MemberDto memberDto = memberMapper.findOneById(authentication.getName());

		MemberLogDto memberLogDto = new MemberLogDto();
		memberLogDto.setName(memberDto.getName());
		memberLogDto.setUsername(memberDto.getUsername());
		memberLogDto.setAddress(addr);
		memberLogDto.setMacAddress(macIp);
		
		memberLogMapper.createLog(memberLogDto);
		
		HttpSession session = request.getSession();    
		String time = memberLogMapper.getLog(memberDto.getUsername());
		
		session.setAttribute("name", memberDto.getName());
		session.setAttribute("time", time);
		
		response.sendRedirect(request.getContextPath()+"/");
	}

}
