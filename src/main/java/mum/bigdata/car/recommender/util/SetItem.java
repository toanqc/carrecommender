package mum.bigdata.car.recommender.util;

public class SetItem {
	private int item;
	private int count;
	
	public SetItem(int item, int count){
		this.item = item;
		this.count = count;
	}
	
	public int getItem(){
		return item;
	}
	
	public int getCount(){
		return count;
	}
}
