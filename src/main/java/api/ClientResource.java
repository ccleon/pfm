package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.ClientController;
import entities.Client;
import wrappers.IdClientWrapper;
import wrappers.ClientCreateWrapper;
import wrappers.ClientWrapper;

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
	
	@RequestMapping(method = RequestMethod.POST)
    public Client createInvoice(@RequestBody ClientCreateWrapper clientCreateWrapper){
    	return clientController.createClient(clientCreateWrapper);
    }
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.GET)  
    public Client getClientById(@PathVariable(value = "id") int id){
        return clientController.getClientById(id);
    }
	
	@RequestMapping(value = Uris.SEARCH, method = RequestMethod.POST)
    public Client searchClient (@RequestBody IdClientWrapper clientWrapper){
    	return clientController.getClientById(clientWrapper.getId());
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void modifyClient (@RequestBody ClientWrapper clientWrapper) {
    	this.clientController.clientModify(clientWrapper);
    }
}
