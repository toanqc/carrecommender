/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.model;

/**
 *
 * @author Hiep
 */
public class Model extends BaseModel {

	private Make make;

	public Model() {
	}

	public Model(long id, String name) {
		super(id, name);
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

}
