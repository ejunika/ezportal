package com.ez.portal.shard.key.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ShardKeyGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor sessionImplementor, Object object) throws HibernateException {
		String prefix = "";
		String suffix = "";
		String generatedValue = "";
		Connection connection = sessionImplementor.connection();
		Statement statement = null;
		String sqlQueryString = null;
		try {
			statement = connection.createStatement();
			sqlQueryString = "SELECT COUNT(user_space_id) as shard_key FROM user_space";
			ResultSet resultSet = statement.executeQuery(sqlQueryString);
			if (resultSet.next()) {
				Integer nextCount = resultSet.getInt(1) + 1;
				generatedValue = prefix + nextCount + suffix;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedValue;
	}

}
