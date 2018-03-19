package com.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.controller.DBController;
import com.database.dao.AdminDao;
import com.database.dao.AuthorityDao;
import com.database.model.Admin;
import com.database.model.Authority;
import com.database.util.AdminException;

@Service
public class AdminService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public AdminService() {}
	
	// id로 admin 가져오기
	public Admin detailId(String id) throws AdminException {
		return DBController.Instance().selectAdmin(id);
	}
	
	// 권한 가져오기
	public Authority getAuthority(Integer id) {
		return DBController.Instance().selectAuthority(id);
	}
	
	// 로그인 한 사용자 객체 가져오기
	public UserDetails getPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = auth.getPrincipal();
		if(principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		
		return null;
	}
	
	// 로그인한 사용자 암호화된 비밀번호 가져와 일치하는지 비교
	public boolean isPasswordMatched(String oldPassword) throws AdminException {
		String id = this.getPrincipal().getUsername();
		Admin admin = DBController.Instance().selectAdmin(id);
		
		return passwordEncoder.matches(oldPassword, admin.getPassword());
	}
	
	// 로그아웃
	public void logout(HttpServletRequest request, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, resp, auth);
		}
	}
	
	
	
}
