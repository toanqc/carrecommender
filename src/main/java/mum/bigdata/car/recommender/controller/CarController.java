package mum.bigdata.car.recommender.controller;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {

	@RequestMapping(value = "car", method = RequestMethod.GET)
	public String getCar(HttpServletRequest request) {
		// Get the car information using ID from GET request
		
		return "cardetails";
	}
	
}
