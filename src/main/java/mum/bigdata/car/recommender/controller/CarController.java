package mum.bigdata.car.recommender.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import mum.bigdata.car.recommender.model.Make;
import mum.bigdata.car.recommender.model.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarController {

    @RequestMapping(value = "route", method = RequestMethod.GET)
    public String router(HttpServletRequest request) {
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

    @RequestMapping(value="makes")
    public @ResponseBody List<Make> getMakes(HttpServletRequest request){
        
        List<Make> makes = new ArrayList<>();
        makes.add(new Make(1,"Acura"));
        makes.add(new Make(2,"Alfa Romeo"));
        makes.add(new Make(3,"Audi"));
        makes.add(new Make(4,"BMW"));
        makes.add(new Make(5,"Bentley"));
        makes.add(new Make(6,"Buick"));

        return makes;
    }
    
    @RequestMapping(value="models")
    public @ResponseBody List<Model> getModels(HttpServletRequest request){
        
        List<Model> models = new ArrayList<>();
        models.add(new Model(3, "A3 Sedan"));
        models.add(new Model(5,"11A5 Coupe"));

        return models;
    }
    
    @RequestMapping(value="years")
    public @ResponseBody List<Model> getYears(HttpServletRequest request){
        
        List<Model> models = new ArrayList<>();
        models.add(new Model(1, "2015"));
        models.add(new Model(2,"2016"));

        return models;
    }
    
}
