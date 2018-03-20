package com.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.admin.service.AdminService;
import com.admin.service.FileService;
import com.admin.service.GameService;
import com.database.model.Admin;
import com.database.model.Game;
import com.database.util.AdminException;
import com.database.util.CustomException;
import com.database.util.FileException;

@Controller
@RequestMapping("/admin")
public class AdminWebController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private GameService gameService;
	

	// 게임리스트
	@RequestMapping(value="/gameList.do", method=RequestMethod.GET)
	public String gameList(Model model) {
		List<Game> list = null;
		try {
			list = gameService.allGame();
		} catch (CustomException e) {
			e.printStackTrace();
		} //DBController.Instance().selectAllGame();
		
		model.addAttribute("list", list);
		
		return "gameList";
	}
	
	// 게임리스트 상세화면 
	@RequestMapping(value="/gameListDetail.do", method=RequestMethod.GET)
	public String gameListDetail(Model model, HttpServletRequest request,
			@RequestParam(value="gameNo", required=true) Integer gameNo)  {
		Game game = null;
		String filename = null;
		String imgPath = null;
		String uploadPath = null;
		
		try {
			game = gameService.detailGame(gameNo); //DBController.Instance().selectGameDetail(gameNo);
			
			filename = game.getCoverImage();
			
			if(filename != null && !filename.trim().isEmpty()) {
				
				filename = URLDecoder.decode(filename, "UTF-8");
			} 
			
			imgPath = fileService.getImgPath(request, filename);
			
			uploadPath = fileService.getUploadPath(request);
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "encoding");
		} catch (CustomException e) {
			
			e.printStackTrace();
		}

		model.addAttribute("game", game);
		model.addAttribute("filename", filename);
		
		if(imgPath != null && !imgPath.trim().isEmpty()) {
			model.addAttribute("imgPath", imgPath);
		}
		
		model.addAttribute("uploadpath", uploadPath);
		
		return "gameListDetail";
	}
	
	// 게임리스트 작성화면
	@RequestMapping(value="/gameListAdd.do", method=RequestMethod.GET)
	public String gameadd(Model model) {
		
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
		
		return "gameListAdd";
	}
	
	
	// 게임리스트 추가 후 게임리스트 목록으로 이동
	@RequestMapping(value="/gameListAdd.do", method=RequestMethod.POST)
	public String add(HttpServletRequest request,
			String title,
			String description,
			String state,
			String version,
			String fileName,
			@RequestParam("coverImage") MultipartFile coverImage) {
		
	Game game = new Game();
	game.setTitle(title);
	game.setDescription(description);
	game.setState(state);
	game.setVersion(version);
	game.setFileName(fileName);
	
	try {
		String filename = fileService.add(request, coverImage);
		game.setCoverImage(filename);
		gameService.add(game);
		
	} catch (CustomException e) {
		request.setAttribute("error", "server");
	} catch (FileException e) {
		request.setAttribute("error", "file");
	}
		
	return "redirect:gameList.do";
		
	}
	
	// 서브이미지 추가 화면 이동
	@RequestMapping(value="/subimage.do", method=RequestMethod.GET)
	public String subImage(Model model) {
		return "subimage";
	}
	
	// 게임리스트 수정 화면
	public String modify(Model model, @RequestParam(value="gameNo", required=true) Integer gameNo) {
		Game game = null;
		
		try {
			String id = adminService.getPrincipal().getUsername();
			model.addAttribute("id", id);
		
			Admin admin = adminService.detailId(id);
			admin.setPassword(null);
			model.addAttribute("admin", admin);
		
			game = gameService.detailGame(gameNo);
			model.addAttribute("game", game);
		
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		} catch (CustomException e) {
			
		}
		
		return "gameListModify";
	}
	
	// 수정 후 게임 목록으로 이동
	public String modify(HttpServletRequest request,
			String title,
			String description,
			String state,
			String version,
			String fileName,
			@RequestParam("coverImage") MultipartFile coverImage) {
		
		
		
		return "redirect:gameList.do";
	}
	
	
	
	
	
	
}



















