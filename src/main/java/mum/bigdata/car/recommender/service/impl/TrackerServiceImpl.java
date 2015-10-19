package mum.bigdata.car.recommender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.bigdata.car.recommender.model.Tracker;
import mum.bigdata.car.recommender.repository.TrackerRepository;
import mum.bigdata.car.recommender.service.TrackerService;

@Service
public class TrackerServiceImpl implements TrackerService {

	@Autowired
	private TrackerRepository trackerRepository;

	@Override
	public Tracker save(Tracker tracker) {
		return trackerRepository.save(tracker);
	}

	@Override
	public Tracker get(String userId) {
		return trackerRepository.get(userId);
	}

}
