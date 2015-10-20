/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mum.bigdata.car.recommender.model.Car;
import mum.bigdata.car.recommender.repository.CarRepository;
import mum.bigdata.car.recommender.repository.RepositoryAbst;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hiep
 */
@Repository
public class CarRepositoryImpl extends RepositoryAbst<Car> implements CarRepository{

    @Override
    public Car getCar(long cid) {
        String sql = String.format("select * from car where cid='%d'", cid);
        try {
            return this.get(sql).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(MakeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Car> getCars(String make, String model, String year) {
        String sql = String.format("select * from car where make='%s' and model='%s' and year='%s'", make, model, year);
        try {
            return this.get(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MakeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Car> buildResult(ResultSet rs) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while(rs.next()){
            Car car = new Car();
            car.setCid(rs.getLong("car.cid"));
            car.setName(rs.getString("car.name"));
            car.setMake(rs.getString("car.make"));
            car.setModel(rs.getString("car.model"));
            car.setYear(rs.getInt("car.year"));
            car.setMpg(rs.getString("car.mpg"));
            car.setTranmision(rs.getString("car.transmission"));
            car.setDoors(rs.getByte("car.doors"));
            car.setSubmodel(rs.getString("car.submodel"));
            car.setMsrp(rs.getInt("car.msrp"));
            car.setRating(rs.getString("car.rating"));
            car.setConsumer_rating(rs.getString("car.customer_rating"));
            car.setPhoto(rs.getString("car.photo"));            
            cars.add(car);
        }
        return cars;
    }
    
}
