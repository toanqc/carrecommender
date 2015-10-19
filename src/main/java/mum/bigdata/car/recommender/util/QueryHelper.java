package mum.bigdata.car.recommender.util;

import java.util.ArrayList;
import java.util.Arrays;

public class QueryHelper {
	
    public static String formatToLikeQuery(ArrayList<Integer> set, String columnName){
    	String cond = Arrays.deepToString(set.toArray());      
    	cond = cond.replace(", ", ",%' OR " + columnName + " LIKE '%,").replace("[", columnName + " LIKE '%,").replace("]", ",%' ");
        return cond;
    }

}
