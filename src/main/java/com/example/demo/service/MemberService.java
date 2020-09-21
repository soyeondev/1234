package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.service.mapper.MemberMapper;
import com.example.demo.service.mapper.RoleMapper;
import com.example.demo.service.mapper.UserMapper;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화
    	System.out.println("회원가입 in");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println(userDto.getUsername());
        
        RoleDto roleDto = new RoleDto();
        roleDto.setUsername(userDto.getUsername());
        roleDto.setRole("MEMBER");
        
        userMapper.insertUser(userDto);
        return roleMapper.insertRole(roleDto);
        //return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        //Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
    	System.out.println("loadUser in");
    	//MemberDto memberDto = memberMapper.findOneById(userEmail);
    	UserDto userDto = userMapper.findOneById(userEmail);
        
    	System.out.println("id: "+userEmail);
        //System.out.println(memberDto.getPassword());
        
        //MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        if (("cat").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
        }

        return new User(userDto.getUsername(), userDto.getPassword(), authorities);
    }
}
