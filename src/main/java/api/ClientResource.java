package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import controllers.ClientController;
import entities.Client;

@RestController
@RequestMapping(Uris.CLIENTS)
public class ClientResource {

	private ClientController clientController;
	
	@Autowired
	public void setClientController(ClientController clientController){
		this.clientController = clientController;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Client> listClients(){
		return clientController.getAll();
	}
}
