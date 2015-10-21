/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import mum.bigdata.car.recommender.model.BaseModel;
import mum.bigdata.car.recommender.repository.util.HiveConnectionManager;

/**
 *
 * @author Hiep
 * @param <T>
 */
public abstract class RepositoryAbst<T extends BaseModel> {

	protected HiveConnectionManager cm;

	public RepositoryAbst() {
		cm = HiveConnectionManager.getInstance();
	}

	public List<T> get(String sql) throws SQLException {
		PreparedStatement statement = select(sql);
		ResultSet rs = statement.executeQuery();
		return buildResult(rs);
	}

	private PreparedStatement select(String sql) throws SQLException {
		Connection conn = cm.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		return statement;
	}

	public abstract List<T> buildResult(ResultSet rs) throws SQLException;

}
