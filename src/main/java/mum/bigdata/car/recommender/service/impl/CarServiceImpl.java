/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.service.impl;

import java.util.List;
import mum.bigdata.car.recommender.model.Car;
import mum.bigdata.car.recommender.repository.CarRepository;
import mum.bigdata.car.recommender.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hiep
 */
@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository repository;
    
    @Override
    public List<Car> getCars(String make, String model, String year) {
        return repository.getCars(make, model, year);
    }
    
}
