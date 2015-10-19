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
import mum.bigdata.car.recommender.util.SetItem;

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
    	
    	data.add(new int[]{200703618,200703609,200703608,200703614,200703615,200703610,200673635});  	
    	data.add(new int[]{200673635,200703610,200703615,200703614,200722371,200703609,200703618,200708065});  
    	data.add(new int[]{200703610,200703615,200703609,200708065,200703614,200673635,200703609});  
    	data.add(new int[]{200703618,200708065,200703609,200722371,200673635,200703615,200703614});  
    	
    	try{
    		Apriori ap = new Apriori(data, 3);
    	    ArrayList<SetItem> set = ap.calculateFrequentItemsets(ap.createList());
    		ArrayList<Integer> resultSet = ap.getSortedSet(set);
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