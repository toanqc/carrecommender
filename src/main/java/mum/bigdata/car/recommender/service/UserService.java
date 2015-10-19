package mum.bigdata.car.recommender.service;

import mum.bigdata.car.recommender.model.User;

public interface UserService {

	/**
	 * 
	 * @param t
	 * @return
	 */
	User saveUser(User t);

	/**
	 * 
	 * @param t
	 * @return
	 */
	User getUser(String id);
}
