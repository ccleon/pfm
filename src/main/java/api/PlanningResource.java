package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import controllers.PlanningController;
import entities.Booking;
import wrappers.PlanningWrapper;

@RestController
@RequestMapping(Uris.PLANNING)
public class PlanningResource {

	private PlanningController planningController;
	
	@Autowired
	public void setPlanningController(PlanningController planningController){
		this.planningController = planningController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public List<Booking> showPlanning(@RequestBody PlanningWrapper planningWrapper){
		System.out.println(planningWrapper.toString());
    	return planningController.getBookingsByMonth(planningWrapper);
    }
}
