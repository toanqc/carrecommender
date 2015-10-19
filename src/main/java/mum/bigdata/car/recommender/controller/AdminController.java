package mum.bigdata.car.recommender.controller;

import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String showMainPage(HttpServletRequest request){
		
		return "admin";
	}

	@RequestMapping(value = "update-rules", method = RequestMethod.GET)
	public String generateRuleSet(HttpServletRequest request){
		
		// TODO: Retrieve the trace column in tracker table
		// TODO: convert result into 2-D array
		// TODO: use Apriori to get the frequent item set
		
		return "admin";
	}	
	
}