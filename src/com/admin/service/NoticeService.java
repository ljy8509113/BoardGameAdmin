package com.admin.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.database.dao.NoticeDao;
import com.database.model.Notice;
import com.database.util.CustomException;

@Service
public class NoticeService {

	NoticeDao dao;
	public NoticeService() {
		dao = new NoticeDao();
	}
	
	public List<Notice> selectAll(Integer gameNo) throws CustomException {
		return dao.selectAll(gameNo);
	}
	
	public Notice select(Integer no) throws CustomException {
		Notice notice = dao.select(no);
		try {
			Date regdate = notice.getRegDate();
			DateFormat original_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = original_format.format(regdate);
			Date new_regdate = original_format.parse(date);
			notice.setRegDate(new_regdate);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		return notice;
	}
	
	public void add(Notice notice) throws CustomException {
		dao.insert(notice);
	}
	
	public void modify(Notice notice) throws CustomException {
		dao.update(notice);
	}
	
	public void delete(Integer no) throws CustomException {
		dao.delete(no);
	}
}
