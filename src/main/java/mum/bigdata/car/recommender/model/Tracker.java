package mum.bigdata.car.recommender.model;

public class Tracker {

	private long id;

	private String userId;

	private String carTrace;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the carTrace
	 */
	public String getCarTrace() {
		return carTrace;
	}

	/**
	 * @param carTrace
	 *            the carTrace to set
	 */
	public void setCarTrace(String carTrace) {
		this.carTrace = carTrace;
	}
}
