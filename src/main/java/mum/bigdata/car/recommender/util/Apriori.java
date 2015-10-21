package mum.bigdata.car.recommender.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Apriori algorithm implementation (https://en.wikipedia.org/wiki/Apriori_algorithm)
 * Returns frequent item set {item, frequency} given a minimum support
 * @author Mark Pit
 */
public class Apriori {

	public static void main(String[] args) throws Exception {
		List<List<String>> data = new ArrayList<List<String>>();

		data.add(Arrays.asList(new String[] { "5", "2", "1", "9", "4", "3", "12" }));
		data.add(Arrays.asList(new String[] { "7", "3", "4", "5", "6", "1", "2" }));

		Apriori ap = new Apriori(data, 1);
		ArrayList<String> resultSet = ap.getItemSet();
		System.out.println(Arrays.deepToString(resultSet.toArray()));
		System.out.println(QueryHelper.formatToLikeQuery(resultSet, "cartrace"));
	}

	private List<List<String>> dataSet;
	private double minSup;

	public Apriori(List<List<String>> data, int s) {
		dataSet = data;
		minSup = s;
	}

	public ArrayList<String> getItemSet() {
		ArrayList<SetItem> resultSet = calculateFrequentItemsets(createList());
		return getSortedSet(resultSet);
	}

	public ArrayList<String> createList() {
		if (dataSet == null) {
			return null;
		}

		ArrayList<String> items = new ArrayList<String>();

		for (int i = 0; i < dataSet.size(); i++) {
			List<String> row = dataSet.get(i);

			for (int j = 0; j < row.size(); j++) {
				items.add(row.get(j));
			}
		}

		return items;
	}

	public ArrayList<SetItem> calculateFrequentItemsets(ArrayList<String> set) {
		if (set == null || set.isEmpty()) {
			return null;
		}

		ArrayList<SetItem> resultSet = new ArrayList<SetItem>();
		int count, i, j;
		int size = set.size();

		Collections.sort(set);

		// Debug
		System.out.println(Arrays.deepToString(set.toArray()));

		for (i = 0; i < size; i++) {
			count = 0;

			String item = set.get(i);
			count++;

			for (j = i + 1; j < size; j++) {
				if (set.get(j) != item) {
					break;
				}

				count++;
			}

			i = j - 1;

			if (count >= minSup) {
				resultSet.add(new SetItem(item, count));
			}
		}

		// Debug
		for (SetItem t : resultSet) {
			System.out.print(t.getItem() + "=" + t.getCount() + ",");
		}
		System.out.println();

		return resultSet;
	}

	public ArrayList<String> getSortedSet(ArrayList<SetItem> set) {
		Collections.sort(set, new MyComparator());
		ArrayList<String> result = new ArrayList<String>();

		for (SetItem t : set) {
			result.add(t.getItem());
		}

		return result;
	}

}

class MyComparator implements Comparator<SetItem> {

	@Override
	public int compare(SetItem n1, SetItem n2) {

		if (n1.getCount() < n2.getCount()) {
			return 1;
		} else {
			return -1;
		}
	}

}