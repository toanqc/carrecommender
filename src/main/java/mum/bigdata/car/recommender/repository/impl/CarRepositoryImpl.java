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
    public Car getCar(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            car.setId(rs.getLong("car.id"));
            car.setName(rs.getString("car.name"));
            car.setPhoto(rs.getString("car.photo"));
            cars.add(car);
        }
        return cars;
    }
    
}
