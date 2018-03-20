package com.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.database.dao.GameDao;
import com.database.model.Game;
import com.database.util.CustomException;

@Service
public class GameService {
	
	GameDao dao;
	public GameService() {
		dao = new GameDao();
	}
	
	
	public void add(Game game) throws CustomException {
		//DBController.Instance().insertGame(game);
		dao.insert(game);		
	}
	
	public String modify(Game game) throws CustomException {
		Game item = dao.select(game.getGameNo()); //DBController.Instance().selectGameDetail(game.getGameNo());
		String filename = item.getCoverImage();
		dao.update(item);//DBController.Instance().updateGame(game);
		
		return filename;
	}
	
	public Game detailGame(Integer gameNo) throws CustomException {
		return dao.select(gameNo); //DBController.Instance().selectGameDetail(gameNo);
	}
	
	public List<Game> allGame() throws CustomException{
		return dao.selectAll();
	}

}
