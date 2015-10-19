package mum.bigdata.car.recommender.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mum.bigdata.car.recommender.util.Apriori;
import mum.bigdata.car.recommender.util.QueryHelper;

@Controller
public class AdminController {
	
	@RequestMapping(value = "adm", method = RequestMethod.GET)
	public String showMainPage(HttpServletRequest request){
		
		return "admin";
	}

	@RequestMapping(value = "rec", method = RequestMethod.POST)
	public String recommendation(HttpServletRequest request, Model model){
    	List<int[]> data = new ArrayList<int[]>();
    	String list = new String();
    	String query = new String();
    	
    	if( ! request.getParameter("input").isEmpty() ){  
    		String[] st = request.getParameter("input").split("\\s*,\\s*");
    		int[] t = new int[st.length];
    		
    		for(int i = 0; i < st.length; i++){
    			t[i] = Integer.parseInt(st[i]);
    		}
    		data.add(t);
    	}
    	
    	data.add(new int[]{3,2,1,3,4});  	
    	data.add(new int[]{1,9,3,4,7,3,2,5});  
    	data.add(new int[]{7,3,4,5,6,1,2});  
    	
    	try{
    		Apriori ap = new Apriori(data, 3);
    		ArrayList<Integer> resultSet = ap.getItemSet();
    		resultSet = ap.getItemSet();
    		list = Arrays.deepToString(resultSet.toArray());
    		query = QueryHelper.formatToLikeQuery(resultSet, "cartrace");
    	} catch (Exception e){
    		System.out.println(e.getMessage());
    	}
    	
    	model.addAttribute("output", list);
    	model.addAttribute("query", "SELECT * FROM car WHERE " + query);
		return "admin";
	}	
	
}