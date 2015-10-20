package mum.bigdata.car.recommender.util;

/*
 * @author Mark Pit
 */
public class SetItem {
	private String item;
	private int count;
	
	public SetItem(String item, int count){
		this.item = item;
		this.count = count;
	}
	
	public String getItem(){
		return item;
	}
	
	public int getCount(){
		return count;
	}
}
