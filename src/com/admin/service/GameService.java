package com.admin.service;

import org.springframework.stereotype.Service;

import com.database.controller.DBController;
import com.database.model.Game;
import com.database.util.CustomException;

@Service
public class GameService {
	
	public GameService() {}
	
	
	public void add(Game game) throws CustomException {
		DBController.Instance().insertGame(game);
	}
	
	public String modify(Game game) throws CustomException {
		Game item = DBController.Instance().selectGameDetail(game.getGameNo());
		String filename = item.getCoverImage();

		DBController.Instance().updateGame(game);
		
		return filename;
	}
	
	public Game detailGame(Integer gameNo) {
		return DBController.Instance().selectGameDetail(gameNo);
	}

}
