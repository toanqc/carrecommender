/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.repository.impl;

import mum.bigdata.car.recommender.repository.ModelRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mum.bigdata.car.recommender.repository.RepositoryAbst;
import mum.bigdata.car.recommender.model.Model;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hiep
 */
@Repository
public class ModelRepositoryImpl extends RepositoryAbst<Model> implements ModelRepository{

    @Override
    public List<Model> buildResult(ResultSet rs) throws SQLException {
        List<Model> models = new ArrayList<>();
        while(rs.next()){
            Model model = new Model();
            model.setId(rs.getLong("model.id"));
            model.setName(rs.getString("model.model"));
            models.add(model);
        }
        return models;
    }

    @Override
    public List<Model> getModels(long makeId){
        String sql = "Select * from model where make=" + makeId;
//        String sql = String.format("Select model from car where make='%s' group by model" ,makeId);
        try {
            return this.get(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Model> getModels(String makeName) {
//        String sql = "Select * from model where make='" + makeName +"'";
        String sql = String.format("Select model from car where make='%s' group by model" ,makeName);
        try {
            return this.get(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ModelRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
