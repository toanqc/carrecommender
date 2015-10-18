package mum.bigdata.car.recommender.repository;

public interface UserRepository<T> {

	/**
	 * 
	 * @param t
	 * @return
	 */
	T save(T t);

	/**
	 * 
	 * @param id
	 * @return
	 */
	T get(String id);
}
