package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.MemberDto;
import com.example.demo.domain.UserDto;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {
	   @Autowired
	   private MemberService memberService;

	    // 메인 페이지
	    @GetMapping("/")
	    public String index() {
	        return "index";
	    }

	    // 회원가입 페이지
	    @GetMapping("/user/signup")
	    public String dispSignup() {
	        return "signup";
	    }

	    // 회원가입 처리
	    @PostMapping("/user/signup")
	    public String execSignup(MemberDto memberDto) {
	        memberService.joinUser(memberDto);
	        return "redirect:/";
	    }

	    // 로그인 페이지
	    @GetMapping("/user/login")
	    public String dispLogin() {
	        return "login";
	    }

	    // 로그인 결과 페이지
	    @GetMapping("/user/login/result")
	    public String dispLoginResult() {
	        return "loginSuccess";
	    }

	    // 로그아웃 결과 페이지
	    @GetMapping("/user/logout/result")
	    public String dispLogout() {
	        return "logout";
	    }

	    // 접근 거부 페이지
	    @GetMapping("/user/denied")
	    public String dispDenied() {
	        return "denied";
	    }

	    // 내 정보 페이지
	    @GetMapping("/user/info")
	    public String dispMyInfo() {
	        return "myinfo";
	    }

	    // 어드민 페이지
	    @GetMapping("/admin")
	    public String dispAdmin() {
	        return "admin";
	    }
	    
	    @GetMapping("/member/list")
	    public String memberList(Model model) {
	    	List<MemberDto> memberDtos = memberService.memberList();
	    	model.addAttribute("memberDtos", memberDtos);
	    	return "member/list";
	    }
	    
	    @PostMapping("/member/update")
	    public String memberUpdate(MemberDto memberDto){
	    	memberService.updateMember(memberDto);
	    	return "member/list";
	    }
	    
	    /*@RequestMapping(value="/user/signup", method=RequestMethod.POST)
	    public String signUp(@ModelAttribute MemberDto memberDto) {
	    	memberService.joinUser(memberDto);
	    	return "signup";
	    }*/
}
