/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.service;

import java.util.List;

import mum.bigdata.car.recommender.model.Car;

/**
 *
 * @author Hiep
 */
public interface CarService {
	public List<Car> getCars(String make, String model, String year);

	public Car getCar(long cid);
}
