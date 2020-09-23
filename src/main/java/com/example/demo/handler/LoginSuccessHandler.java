package com.example.demo.handler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.domain.UserDto;
import com.example.demo.domain.UserLogDto;
import com.example.demo.service.mapper.UserLogMapper;
import com.example.demo.service.mapper.UserMapper;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private UserLogMapper userLogMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("success handler");
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
		
		UserDto userDto = userMapper.findOneById(authentication.getName());
		UserLogDto userLogDto = new UserLogDto();
		userLogDto.setName(userDto.getName());
		userLogDto.setUsername(userDto.getUsername());
		userLogDto.setAddress(addr);
		userLogDto.setMacAddress(macIp);
		
		userLogMapper.createLog(userLogDto);
		
		HttpSession session = request.getSession();
		
//		SimpleDateFormat format = new SimpleDateFormat("HH시 mm분");
//		Date time = new Date();
//		String time1 = format.format(time);
		//String time = userLogMapper.getLog(authentication.getName()).getCreateAt();
	
		System.out.println("getUsername():"+userDto.getUsername());
		String time = userLogMapper.getLog(userDto.getUsername());
		System.out.println(time.toString());
		
		session.setAttribute("name", userDto.getName());
		session.setAttribute("time", time);
		
		response.sendRedirect("/");
	}

}
