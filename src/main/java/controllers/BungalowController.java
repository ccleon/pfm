package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BungalowDao;
import entities.Bungalow;

@Controller
public class BungalowController {

	private BungalowDao bungalowDao;
	
	@Autowired
	public void setBungalowDao(BungalowDao bungalowDao) {
		this.bungalowDao = bungalowDao;
	}
	
	public List<Bungalow> getAll(){
		List<Bungalow> bungalows = bungalowDao.findAll();
		return bungalows;
	}

}
