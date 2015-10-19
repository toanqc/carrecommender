package mum.bigdata.car.recommender.repository.impl;

import org.springframework.stereotype.Repository;

import mum.bigdata.car.recommender.model.Tracker;
import mum.bigdata.car.recommender.repository.TrackerRepository;

@Repository
public class TrackerRepositoryImpl implements TrackerRepository {

	@Override
	public Tracker save(Tracker tracker) {
		throw new UnsupportedOperationException("need implemetation");
	}

	@Override
	public Tracker get(String userId) {
		throw new UnsupportedOperationException("need implemetation");
	}

}
