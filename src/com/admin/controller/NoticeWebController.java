package com.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.service.AdminService;
import com.admin.service.NoticeService;
import com.database.model.Admin;
import com.database.model.Notice;
import com.database.util.AdminException;
import com.database.util.CustomException;

@Controller
@RequestMapping("/admin")
public class NoticeWebController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AdminService adminService;

	// 공지사항 목록
	@RequestMapping(value="/noticeList.do", method=RequestMethod.GET)
	public String noticList(Model model, 
			@RequestParam(value="gameNo", required=true) Integer gameNo) {
		
		List<Notice> list = null;
		try {
			list = noticeService.selectAll(gameNo);
			model.addAttribute("list", list);
		} catch (CustomException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		return "noticeList";
	}
	
	// 공지사항 자세히 보기
	@RequestMapping(value="/noticedetail.do", method=RequestMethod.GET)
	public String noticeDetail(Model model, @RequestParam(value="no", required=true) Integer no) {
		Notice notice = null;
		String id = adminService.getPrincipal().getUsername();
		model.addAttribute("id", id);
		try {
			Admin admin = adminService.detailId(id);
			admin.setPassword(null);
			model.addAttribute("admin", admin);
			
			notice = noticeService.select(no);

		} catch (CustomException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} catch (AdminException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} 
		model.addAttribute("notice", notice);
		
		return "noticeDetail";
	}
	
	// 공지사항 등록 화면가기
	@RequestMapping(value="/noticeAdd.do", method=RequestMethod.GET)
	public String noticeAdd(Model model) {
		String id = adminService.getPrincipal().getUsername();
		model.addAttribute("id", id);
		try {
			Admin admin = adminService.detailId(id);
			admin.setPassword(null);
			model.addAttribute("admin", admin);
		} catch (AdminException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		return "noticeAdd";
	}
	
	// 공지사항 등록 후 목록 화면으로 이동
	@RequestMapping(value="/noticeAdd.do", method=RequestMethod.POST)
	public String noticAdd(HttpServletRequest request,
			String writer,
			String title,
			String description,
			String type,
			String link,
			String showTime,
			String endTime,
			Integer gameNo) {
		
		Date showdate = null;
		Date enddate = null;
		
		try {
			showdate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(showTime);
			enddate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(endTime);
			
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		Notice notice = new Notice();
		notice.setWriter(writer);
		notice.setTitle(title);
		notice.setDescription(description);
		notice.setType(type);
		notice.setLink(link);
		notice.setShowTime(showdate);
		notice.setEndTime(enddate);
		notice.setGameNo(gameNo);
		
		try {
			noticeService.add(notice);
		} catch (CustomException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		}
		
		return "redirect:noticeList.do?gameNo=" + gameNo;
	}
	
	// 공지사항 수정
	@RequestMapping(value="/noticeModify.do", method=RequestMethod.GET)
	public String modify(Model model, @RequestParam(value="no", required=true) Integer no) {
		Notice notice = null;
		String id = adminService.getPrincipal().getUsername();
		model.addAttribute("id", id);
		
		try {
			Admin admin = adminService.detailId(id);
			admin.setPassword(null);
			model.addAttribute("admin", admin);
			
			notice = noticeService.select(no);
		} catch (CustomException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("notice", notice);
		
		return "noticeModify";
	}
	
	// 공지사항 수정 후 
	@RequestMapping(value="/noticeModify.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, 
			Integer no,
			String title,
			String description,
			String showTime,
			String endTime,
			String type,
			String link,
			Integer gameNo,
			String writer) {
		Date showdate = null;
		Date enddate = null;
		try {
			showdate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(showTime);
			enddate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(endTime);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		Notice notice = new Notice();
		notice.setNo(no);
		notice.setTitle(title);
		notice.setDescription(description);
		notice.setShowTime(showdate);
		notice.setEndTime(enddate);
		notice.setType(type);
		notice.setLink(link);
		notice.setGameNo(gameNo);
		notice.setWriter(writer);
		
		try {
			noticeService.modify(notice);
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:noticeList.do?gameNo=" + 1;
	}
	
	// 공지사항 삭제 확인
	@RequestMapping(value="/noticeRemove.do", method=RequestMethod.GET)
	public String noticeRemove(Model model, @RequestParam(value="no", required=true) Integer no) {
		model.addAttribute("no", no);
		return "delete-confirm";
	}
	
	// 삭제 후, 공지사항 목록으로 이동
	@RequestMapping(value="/noticeRemove.do", method=RequestMethod.POST)
	public String noticeRemove(Model model, Integer no, HttpServletRequest request) {
		try {
			noticeService.delete(no);
		} catch (CustomException e) {
			model.addAttribute("error", "server");
			System.out.println(e.getMessage());
		}
		return "redirect:noticeList.do?gameNo=" + 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

