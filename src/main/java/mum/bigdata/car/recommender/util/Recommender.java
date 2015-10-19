package mum.bigdata.car.recommender.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import mum.bigdata.car.recommender.model.Car;
import mum.bigdata.car.recommender.model.Make;
import mum.bigdata.car.recommender.model.Model;
import mum.bigdata.car.recommender.repository.util.HiveConnectionManager;

/*
 * Main class for retrieving data for recommendation
 * @author Mark Pit
 */
public class Recommender {

	private int minimumSupport = 2;
	private String userId;
	private HiveConnectionManager cm = HiveConnectionManager.getInstance();
	
	public Recommender(String userId){
		this.userId = userId;
	}
	
	public void setMinimumSupport(int minSup){
		this.minimumSupport = minSup;
	}
	
	public ArrayList<Car> getRecommendation(){
		if( userId.isEmpty() ) return null;

		ArrayList<String> userCarTrace = getUserCarTrace(userId);
		Apriori ap = new Apriori( getAssociatedTraces(userCarTrace), minimumSupport);
		
	    ArrayList<SetItem> set = ap.calculateFrequentItemsets(ap.createList());
	    ArrayList<String> resultSet = ap.getSortedSet(set);
	
		return getCarDetails(resultSet);
	}
	
	private ArrayList<String> getUserCarTrace(String userId){
		ArrayList<String> userData = new ArrayList<String>();
    	String query = "";

		try (Connection conn = cm.getConnection()) {
			// Get all the user traces
			query = "SELECT cartrace FROM `tracker` WHERE id=?";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, userId);
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				userData.add(rs.getString("cartrace"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return userData;
	}
	
	private List<List<String>> getAssociatedTraces(ArrayList<String> carTrace){
		List<List<String>> associatedList = new ArrayList<List<String>>();

		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> row = new ArrayList<String>();
		String id;
		
		try (Connection conn = cm.getConnection()) {
			// Get all the user traces
			String query = "SELECT id, cartrace FROM `tracker` WHERE (" + QueryHelper.formatToLikeQuery(carTrace, "cartrace") + ")";
			
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				id = rs.getString("id");
				if( map.containsKey(id) ){
					row = map.get(id);
					row.add(rs.getString("cartrace"));
				} else {
					row.clear();
					row.add(rs.getString("cartrace"));
					map.put(id, row);
				}
			}
			
			for (ArrayList<String> entry : map.values()){
				associatedList.add(entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		
		return associatedList;
	}
	
	private ArrayList<Car> getCarDetails(ArrayList<String> carIdList){
		ArrayList<Car> carList = new ArrayList<Car>();
		
		try (Connection conn = cm.getConnection()) {
			String query = QueryHelper.formatToLikeQuery(carIdList, "cartrace");
			query = "SELECT * FROM car WHERE " + query;
			
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Car c = new Car();
				c.setCid(rs.getLong("cid"));
				c.setName(rs.getString("name"));
				c.setMake(rs.getString("make"));
				c.setModel(rs.getString("model"));
				c.setYear(rs.getInt("year"));
				c.setPhoto(rs.getString("photo"));
				carList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return carList;
	}
	
	public void showTracks(){
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
		try (Connection conn = cm.getConnection()) {
			String sql = "SELECT * FROM `user`";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
				//Debug
				System.out.println(rs.getString("id") + ", " + rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}	
	
	public static void main(String[] args){
		Recommender rec = new Recommender("1");
		rec.showTracks();
	}
	
}
