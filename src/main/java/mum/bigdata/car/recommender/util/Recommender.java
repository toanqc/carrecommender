package mum.bigdata.car.recommender.util;

import java.util.ArrayList;
import java.util.Arrays;

import mum.bigdata.car.recommender.util.*;
import mum.bigdata.car.recommender.model.Car;

public class Recommender {

	private int minimumSupport = 2;
	private String userId;
	
	public Recommender(String userId){
		this.userId = userId;
	}
	
	public void setMinimumSupport(int minSup){
		this.minimumSupport = minSup;
	}
	
	public ArrayList<Car> getRecommendation(){
		if( userId.isEmpty() ) return null;
		
		ArrayList<Integer> userCarTrace = getUserCarTrace(userId);
		Apriori ap = new Apriori( getAssociatedTraces(userCarTrace), minimumSupport);
		
	    ArrayList<SetItem> set = ap.calculateFrequentItemsets(ap.createList());
		ArrayList<Integer> resultSet = ap.getSortedSet(set);
	
		return getCarDetails(resultSet);
	}
	
	private ArrayList<Integer> getUserCarTrace(String user_id){
		//TODO: Query user cartrace
		return null;
	}
	
	private ArrayList<int[]> getAssociatedTraces(ArrayList<Integer> carTrace){
		//TODO: Query cartrace from other users
		
		return null;
	}
	
	private ArrayList<Car> getCarDetails(ArrayList<Integer> carIdList){
		String query = QueryHelper.formatToLikeQuery(carIdList, "cartrace");
		query = "SELECT * FROM car WHERE " + query;
		
		//TODO: run query to db, return result as Car list
		
		return null;
	}
	
}
