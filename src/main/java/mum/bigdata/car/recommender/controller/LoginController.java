package mum.bigdata.car.recommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.bigdata.car.recommender.model.User;
import mum.bigdata.car.recommender.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String loginSuccess(@RequestBody User user) {

		// need to proceed the data... insert into database
		userService.saveUser(user);

		return "index";
	}
}
