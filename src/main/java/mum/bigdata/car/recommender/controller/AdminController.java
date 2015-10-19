package mum.bigdata.car.recommender.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mum.bigdata.car.recommender.model.Car;
import mum.bigdata.car.recommender.repository.util.HiveConnectionManager;
import mum.bigdata.car.recommender.util.Apriori;
import mum.bigdata.car.recommender.util.QueryHelper;
import mum.bigdata.car.recommender.util.SetItem;
import mum.bigdata.car.recommender.util.Recommender;

@Controller
public class AdminController {
	
	@RequestMapping(value = "simulator", method = RequestMethod.GET)
	public String showMainPage(HttpServletRequest request){
		
		return "admin";
	}

	@RequestMapping(value = "rec", method = RequestMethod.POST)
	public String recommendation(HttpServletRequest request, Model model){
		ArrayList<Car> userRecommendations = new ArrayList<Car>();

    	String userId = request.getParameter("userId");
    	Recommender rec = new Recommender(userId);
    	userRecommendations = rec.getRecommendation();
    	
    	model.addAttribute("data", userRecommendations);
		return "admin";
		
//    	data.add(new int[]{200703618,200703609,200703608,200703614,200703615,200703610,200673635});  	
//    	data.add(new int[]{200673635,200703610,200703615,200703614,200722371,200703609,200703618,200708065});  
//    	data.add(new int[]{200703610,200703615,200703609,200708065,200703614,200673635,200703609});  
//    	data.add(new int[]{200703618,200708065,200703609,200722371,200673635,200703615,200703614});  
    	
//    	try{
//    		Apriori ap = new Apriori(data, 3);
//    	    ArrayList<SetItem> set = ap.calculateFrequentItemsets(ap.createList());
//    		ArrayList<Integer> resultSet = ap.getSortedSet(set);
//    		list = Arrays.deepToString(resultSet.toArray());
//    		query = QueryHelper.formatToLikeQuery(resultSet, "cartrace");
//    	} catch (Exception e){
//    		System.out.println(e.getMessage());
//    	}
//    	
    	//model.addAttribute("output", list);		
	}	
	
	@RequestMapping(value = "carclick", method = RequestMethod.POST)
	public String saveCarClick(HttpServletRequest request, Model model){
		String userId = request.getParameter("userId");
		String carId = request.getParameter("carId");
		HiveConnectionManager cm = HiveConnectionManager.getInstance();
		
		try (Connection conn = cm.getConnection()) {
			String sql = "INSERT INTO `tracker`(userid, cartrace) VALUES(?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, carId);
			statement.executeUpdate();									
			
			model.addAttribute("carTrace", carId + " was stored successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return "admin";
	}
	
}