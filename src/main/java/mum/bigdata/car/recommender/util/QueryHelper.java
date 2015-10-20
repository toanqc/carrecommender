package mum.bigdata.car.recommender.util;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * @author Mark Pit
 */
public class QueryHelper {
	
    public static String formatToLikeQuery(ArrayList<String> set, String columnName){
    	String cond = Arrays.deepToString(set.toArray());      
    	cond = cond.replace(", ", "%' OR " + columnName + " LIKE '%").replace("[", columnName + " LIKE '%").replace("]", "%' ");
        return cond;
    }

    public static String formatToINQuery(ArrayList<String> set, String columnName){
    	String cond = columnName + " IN (" + Arrays.deepToString(set.toArray()) + ") ";      
    	cond = cond.replace("[", "").replace("]", "");
        return cond;
    }    
    
    public static String formatToEqualQuery(ArrayList<String> set, String columnName){
    	String cond = columnName + "=" + Arrays.deepToString(set.toArray());      
    	cond = cond.replace(", ", "' OR " + columnName + "='").replace("[", "'").replace("]", "'");
        return cond;
    }   
}
