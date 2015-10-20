package mum.bigdata.car.recommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import mum.bigdata.car.recommender.model.User;
import mum.bigdata.car.recommender.service.UserService;

@Controller
@SessionAttributes({ "user" })
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String loginSuccess(@RequestBody User user, ModelMap model) {

		userService.saveUser(user);
		model.addAttribute(user);

		return "index";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutSuccess(SessionStatus status) {
		status.setComplete();

		return "index";
	}
}
