package mum.bigdata.car.recommender.controller;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {

	@RequestMapping(value = "route", method = RequestMethod.GET)
	public String router(HttpServletRequest request){
		// This is the central location for all links to be tracked
		// TODO: Extract URL from request variable
		// TODO: URL can be an internal or external URL
		// TODO: Save Car ID to tracker table using user id
		
		String url = "";
		return "redirect:" + url;
	}	
	
	@RequestMapping(value = "car", method = RequestMethod.GET)
	public String getCar(HttpServletRequest request) {
		// Get the car information using ID from GET request
		
		return "cardetails";
	}
	
}
