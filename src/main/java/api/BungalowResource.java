package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.BungalowController;
import entities.Bungalow;

@RestController
@RequestMapping(Uris.BUNGALOWS)
public class BungalowResource {

	private BungalowController bungalowController;
	
	@Autowired
	public void setBungalowController(BungalowController bungalowController){
		this.bungalowController = bungalowController;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Bungalow> listBungalows(){
		return bungalowController.getAll();
	}
}
