package mum.bigdata.car.recommender.service;

public interface UserService<T> {

	/**
	 * 
	 * @param t
	 * @return
	 */
	T saveUser(T t);

	/**
	 * 
	 * @param t
	 * @return
	 */
	T getUser(String id);
}
