/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.service;

import java.util.List;
import mum.bigdata.car.recommender.model.Model;

/**
 *
 * @author Hiep
 */
public interface ModelService {
    public List<Model> getModels(long makeId);
    public List<Model> getModels(String makeName);
}
