package mum.bigdata.car.recommender.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import mum.bigdata.car.recommender.model.Make;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {	

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() throws SQLException {
        return "index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String getSearch() {
        return "search";
    }

    @RequestMapping(value = "dosearch", method = RequestMethod.GET)
    public String doSearch(HttpServletRequest request) {
        return "search";
    }

}
