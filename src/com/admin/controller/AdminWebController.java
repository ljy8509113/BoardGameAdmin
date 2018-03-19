package com.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.service.AdminService;
import com.database.controller.DBController;
import com.database.model.Game;

@Controller
public class AdminWebController {
	
	@Autowired
	private AdminService adminService;

	
	// 로그인 페이지
	@RequestMapping(value="/login.do", method=RequestMethod.GET)	
	public String login() {
		return "login";
	}
	
	
	// 로그아웃
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse resp) {
		// 서비스의 로그아웃 메소드 호출
		adminService.logout(request, resp);
		
		// 로그아웃 한 뒤 로그인 페이지로 이동 후 로그아웃 메시지 출력을 위해 쿼리문자열 사용
		return "redirect:/login.do?logout=true";
	}
	
	// 접근 제한 페이지
	@RequestMapping(value="/access-denied.do", method=RequestMethod.GET)
	public String accessDenied(Model model) {
		
		model.addAttribute(adminService.getPrincipal().getUsername());
		
		return "access-denied";
	}
	
	@RequestMapping(value="/gameList.do", method=RequestMethod.GET)
	public String gameList(Model model) {
		/*List<Game> list = null;
		
		list = DBController.Instance().selectAllGame();
		
		model.addAttribute("list", list);*/
		
		return "gameList";
		
	}
	
}