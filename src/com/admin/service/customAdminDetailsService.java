package com.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.database.model.Admin;
import com.database.model.Authority;

@Service
public class customAdminDetailsService implements UserDetailsService{

	@Autowired
	private AdminService service;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		try {
			Admin admin = service.detailId(id);

			if(admin == null) {
				throw new UsernameNotFoundException("해당 관리자가 없습니다.");
			}

			return new User(admin.getId(),
					admin.getPassword(),
					true,
					true,
					true,
					true,
					this.getGrantedAuthority(admin));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}



	// 권한 리턴
	private List<GrantedAuthority> getGrantedAuthority(Admin admin) {
		List<GrantedAuthority> auths = new ArrayList<>();
	
		for(Authority item : admin.getAuthorities()) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + item.getName()));
		}
	
		System.out.println(admin.getId() + ", 사용자의 권한 auths: " + auths);
	
		return auths;
	}
}




















