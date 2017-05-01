package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.ClientDao;
import entities.Client;

@Controller
public class ClientController {

	private ClientDao clientDao;
	
	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	public List<Client> getAll(){
		List<Client> clients = clientDao.findAll();
		return clients;
	}
}
