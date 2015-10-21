/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.bigdata.car.recommender.model.Make;
import mum.bigdata.car.recommender.repository.MakeRepository;
import mum.bigdata.car.recommender.service.MakeService;

/**
 *
 * @author Hiep
 */
@Service
public class MakeServiceImpl implements MakeService {

	@Autowired
	private MakeRepository repository;

	@Override
	public List<Make> getMakes() {
		return repository.getMakes();
	}

}
