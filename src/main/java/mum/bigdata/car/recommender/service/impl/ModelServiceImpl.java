/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.bigdata.car.recommender.model.Model;
import mum.bigdata.car.recommender.repository.ModelRepository;
import mum.bigdata.car.recommender.service.ModelService;

/**
 *
 * @author Hiep
 */
@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelRepository repository;

	@Override
	public List<Model> getModels(long makeId) {
		return repository.getModels(makeId);
	}

	@Override
	public List<Model> getModels(String makeName) {
		return repository.getModels(makeName);
	}

}
