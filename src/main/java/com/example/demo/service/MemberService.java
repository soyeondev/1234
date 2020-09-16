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
import com.example.demo.service.mapper.MemberMapper;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;
	
    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
    	System.out.println("회원가입 in");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        System.out.println(memberDto.getUsername());
        memberDto.setRoll("MEMBER");
        
        return memberMapper.insertMember(memberDto);
        //return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        //Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(userEmail);
    	System.out.println("loadUser in");
    	MemberDto memberDto = memberMapper.findOneById(userEmail);
        System.out.println("id: "+userEmail);
        System.out.println(memberDto.getPassword());
        
        //MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        if (("cat").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else {
        }

        return new User(memberDto.getUsername(), memberDto.getPassword(), authorities);
    }
}
