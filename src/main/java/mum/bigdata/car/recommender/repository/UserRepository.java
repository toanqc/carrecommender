package mum.bigdata.car.recommender.repository;

import mum.bigdata.car.recommender.model.User;

public interface UserRepository {

	/**
	 * 
	 * @param t
	 * @return
	 */
	User save(User t);

	/**
	 * 
	 * @param id
	 * @return
	 */
	User get(String id);
}
