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

import org.springframework.stereotype.Repository;

import mum.bigdata.car.recommender.model.Model;
import mum.bigdata.car.recommender.repository.ModelRepository;
import mum.bigdata.car.recommender.repository.RepositoryAbst;

/**
 *
 * @author Hiep
 */
@Repository
public class ModelRepositoryImpl extends RepositoryAbst<Model> implements ModelRepository {

	@Override
	public List<Model> buildResult(ResultSet rs) throws SQLException {
		List<Model> models = new ArrayList<>();
		while (rs.next()) {
			Model model = new Model();
			model.setId(rs.getLong("model.id"));
			model.setName(rs.getString("model.model"));
			models.add(model);
		}
		return models;
	}

	@Override
	public List<Model> getModels(String makeName) {
		String sql = "Select * from model where make='" + makeName + "'";
		try {
			return this.get(sql);
		} catch (SQLException ex) {
			Logger.getLogger(ModelRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@Override
	public List<Model> getModels(long makeId) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
