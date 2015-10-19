package mum.bigdata.car.recommender.service;

import mum.bigdata.car.recommender.model.Tracker;

public interface TrackerService {

	Tracker save(Tracker tracker);

	Tracker get(String userId);
}
