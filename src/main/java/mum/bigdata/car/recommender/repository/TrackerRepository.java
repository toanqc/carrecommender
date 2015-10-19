package mum.bigdata.car.recommender.repository;

import mum.bigdata.car.recommender.model.Tracker;

public interface TrackerRepository {

	/**
	 * 
	 * @param tracker
	 * @return
	 */
	Tracker save(Tracker tracker);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	Tracker get(String userId);
}
