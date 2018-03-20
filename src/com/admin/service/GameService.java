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
	
	public void modify(Game game) throws CustomException {
		DBController.Instance().updateGame(game);
	}

}
