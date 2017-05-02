package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.ClientDao;
import entities.Client;
import wrappers.ClientCreateWrapper;
import wrappers.ClientWrapper;

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
	
	public Client getClientById(long id){
        return clientDao.findOne((int) id);
    }

	public Client createClient (ClientCreateWrapper clientCreateWrapper) {
		Client client = new Client( 
				clientCreateWrapper.getName(), 
				clientCreateWrapper.getSurname(),
				clientCreateWrapper.getAddress(),				
				clientCreateWrapper.getDni());
		
		return clientDao.save(client);
	}
	
	public void clientModify (ClientWrapper clientWrapper) {
		Client client = clientDao.findOne(clientWrapper.getId());
		
		client.setName(clientWrapper.getName());
		client.setSurname(clientWrapper.getSurname());
		client.setDni(clientWrapper.getDni());
		client.setAddress(clientWrapper.getAddress());
		
		this.clientDao.save(client);
	}
    
}
