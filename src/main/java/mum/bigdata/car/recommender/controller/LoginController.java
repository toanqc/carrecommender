package mum.bigdata.car.recommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.bigdata.car.recommender.model.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String loginSuccess(@RequestBody User user) {
		// need to proceed the data... insert into database

		return "index";
	}
}
