package com.admin.service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.database.util.FileException;

@Service
public class FileServiceImpl<T> implements FileService<T>{

	private static final String UPLOAD_FOLDER = "/upload";

	@Override
	public String add(HttpServletRequest request, MultipartFile coverImage) throws FileException {
		try {
			String path = request.getServletContext().getRealPath(UPLOAD_FOLDER);
			String originalName = coverImage.getOriginalFilename();
			File directory = new File(path);
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			if(coverImage != null && !coverImage.isEmpty()) {
				Integer idx = originalName.lastIndexOf(".");
				String name = originalName.substring(0, idx);
				String ext = originalName.substring(idx, originalName.length());
				String uploadFilename = name
						+ Long.toHexString(System.currentTimeMillis())
						+ ext;
			
				coverImage.transferTo(new File(path, uploadFilename));
				uploadFilename = URLEncoder.encode(uploadFilename, "UTF-8");
				
				return uploadFilename;
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FileException(e.getMessage());
		}
		return null;
	}

	@Override
	public void remove(HttpServletRequest request, String filename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void download(HttpServletRequest request, HttpServletResponse response, String filename) {
		// TODO Auto-generated method stub
		
	}

	// 이미지 경로 
	@Override
	public String getImgPath(HttpServletRequest request, String filename) {
		String contextPath = request.getContextPath();
		
		if(filename != null && !filename.trim().isEmpty()) {
			int idx = filename.lastIndexOf(".");
			String ext = filename.substring(idx, filename.length());
			
			switch (ext) {
			case ".jpg":
			case ".jpeg":
			case ".png":
				return contextPath + UPLOAD_FOLDER + "/" + filename;
			}
		}
		return null;
	}

	@Override
	public String getUploadPath(HttpServletRequest request) {
		return request.getContextPath() + UPLOAD_FOLDER;
	}
	
	

}
