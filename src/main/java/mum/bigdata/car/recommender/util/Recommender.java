package mum.bigdata.car.recommender.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import mum.bigdata.car.recommender.util.*;
import mum.bigdata.car.recommender.model.Car;

import mum.bigdata.car.recommender.repository.util.HiveConnectionManager;

public class Recommender {

	private int minimumSupport = 2;
	private String userId;
	private HiveConnectionManager cm;
	
	public Recommender(String userId){
		this.userId = userId;
	}
	
	public void setMinimumSupport(int minSup){
		this.minimumSupport = minSup;
	}
	
	public ArrayList<Car> getRecommendation(){
		if( userId.isEmpty() ) return null;
		
		cm = HiveConnectionManager.getInstance();
		
		ArrayList<Integer> userCarTrace = getUserCarTrace(userId);
		Apriori ap = new Apriori( getAssociatedTraces(userCarTrace), minimumSupport);
		
	    ArrayList<SetItem> set = ap.calculateFrequentItemsets(ap.createList());
		ArrayList<Integer> resultSet = ap.getSortedSet(set);
	
		return getCarDetails(resultSet);
	}
	
	private ArrayList<Integer> getUserCarTrace(String userId){
		//TODO: Query user cartrace
		try (Connection conn = cm.getConnection()) {
			String sql = "SELECT cartrace FROM `user` WHERE id = (?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userId);	
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				//Debug
				System.out.println(rs.getString("cartrace"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
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
	
	public void showTracks(){
		cm = HiveConnectionManager.getInstance();
		
		try (Connection conn = cm.getConnection()) {
			String sql = "SELECT userid, cartrace FROM `tracker`";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				//Debug
				System.out.println(rs.getString("userid") + ", " + rs.getString("cartrace"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	
	public void showUsers(){
		cm = HiveConnectionManager.getInstance();
		
		try (Connection conn = cm.getConnection()) {
			String sql = "SELECT * FROM `user`";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				//Debug
				System.out.println(rs.getString("id") + ", " + rs.getString("firstname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}	
	
	public static void main(String[] args){
		Recommender rec = new Recommender("1");
		rec.showUsers();
	}
	
}
