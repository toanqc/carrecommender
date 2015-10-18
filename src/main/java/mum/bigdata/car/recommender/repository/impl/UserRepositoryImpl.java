package mum.bigdata.car.recommender.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mum.bigdata.car.recommender.model.User;
import mum.bigdata.car.recommender.repository.UserRepository;
import mum.bigdata.car.recommender.repository.util.HiveConnectionManager;

public class UserRepositoryImpl implements UserRepository<User> {

	private HiveConnectionManager cm;

	public UserRepositoryImpl() {
		cm = HiveConnectionManager.getInstance();
	}

	@Override
	public User save(User user) {
		try (Connection conn = cm.getConnection()) {
			PreparedStatement statement = insertUser(conn, user);
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private PreparedStatement insertUser(Connection conn, User user) throws SQLException {
		String sql = "INSERT INTO User(id, name, email) VALUES(?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, user.getId());
		statement.setString(2, user.getName());
		statement.setString(3, user.getEmail());

		return statement;
	}

	@Override
	public User get(String id) {
		try (Connection conn = cm.getConnection()) {
			PreparedStatement statement = selectUser(conn, id);
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				return buildUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private PreparedStatement selectUser(Connection conn, String id) throws SQLException {
		String sql = "SELECT * FROM user WHERE id=?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, id);
		return statement;
	}

	private User buildUser(ResultSet rs) throws SQLException {
		return new User(rs.getString("id"), rs.getString("name"), rs.getString("email"));
	}
}
