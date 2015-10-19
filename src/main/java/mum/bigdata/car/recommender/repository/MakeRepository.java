/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.repository;

import java.util.List;
import mum.bigdata.car.recommender.model.Make;

/**
 *
 * @author Hiep
 */
public interface MakeRepository {
    public List<Make> getMakes();
}
