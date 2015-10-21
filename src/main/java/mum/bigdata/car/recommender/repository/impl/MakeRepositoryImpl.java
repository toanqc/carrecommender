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

import mum.bigdata.car.recommender.model.Make;
import mum.bigdata.car.recommender.repository.MakeRepository;
import mum.bigdata.car.recommender.repository.RepositoryAbst;

/**
 *
 * @author Hiep
 */
@Repository
public class MakeRepositoryImpl extends RepositoryAbst<Make> implements MakeRepository {

	@Override
	public List<Make> buildResult(ResultSet rs) throws SQLException {
		List<Make> makes = new ArrayList<>();
		while (rs.next()) {
			Make make = new Make();
			make.setId(rs.getLong("make.id"));
			make.setName(rs.getString("make.name"));
			makes.add(make);
		}
		return makes;
	}

	@Override
	public List<Make> getMakes() {
		String sql = "Select * from make";
		try {
			return this.get(sql);
		} catch (SQLException ex) {
			Logger.getLogger(MakeRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
