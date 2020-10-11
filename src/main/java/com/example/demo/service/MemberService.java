package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.MemberDto;
import com.example.demo.domain.RoleDto;
import com.example.demo.domain.UserDto;
import com.example.demo.handler.LoginSuccessHandler;
import com.example.demo.service.mapper.MemberMapper;
import com.example.demo.service.mapper.RoleMapper;
import com.example.demo.service.mapper.UserMapper;

@Service
public class MemberService implements UserDetailsService {

	protected static Logger LOGGER = Logger.getLogger(MemberService.class.getName());

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
    	LOGGER.info("joinUser in");
    	LOGGER.info(memberDto.getUsername());

    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        
        RoleDto roleDto = new RoleDto();
        roleDto.setUsername(memberDto.getUsername());
        roleDto.setRole("MEMBER");
        
        memberMapper.insertMember(memberDto);
        return roleMapper.insertRole(roleDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	LOGGER.info("loadUserByUsername in");
    	
    	MemberDto memberDto = memberMapper.findOneById(username);
        //System.out.println(memberDto.getPassword());
        
        //MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        if (("cat").equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
        }

        return new User(memberDto.getUsername(), memberDto.getPassword(), authorities);
    }
    
    public List<MemberDto> memberList(){
    	List<MemberDto> memberList = new ArrayList<MemberDto>();
    	memberList = memberMapper.memberList();
    	
		/*
		 * SimpleDateFormat toStrFmt = new SimpleDateFormat("yyyy-MM-dd");
		 * SimpleDateFormat toDateFmt = new SimpleDateFormat("yyyy-MM-dd"); String
		 * strCreateAt;
		 Date dateCreateAt;
    	
    	for(int i = 0; i < memberList.size(); i++) {
    		if(memberList.get(i).getCreateAt() != null) {
    		try {
    			strCreateAt = toStrFmt.format(memberList.get(i).getCreateAt());
    			LOGGER.info(toStrFmt);
				dateCreateAt = toDateFmt.parse(strCreateAt);
				memberList.get(i).setCreateAt(dateCreateAt);

    		} catch (ParseException e) {
				e.printStackTrace();
			}
    		
    	}
    	}*/
    	LOGGER.info(memberList.get(5).getCreateAt());
    	LOGGER.info(memberList.get(4).getCreateAt());
    	LOGGER.info(memberList.get(5).toString());
    	
    	return memberList;
    }

    @Transactional
	public void updateMember(MemberDto memberDto) {
		memberMapper.memberUpdate(memberDto);
	}
}
