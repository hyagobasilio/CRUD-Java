package br.com.tita.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String HOST = "jdbc:mysql://localhost/";
	private static final String BANCO = "agenda";
	private static final String USUARIO = "root";
	private static final String SENHA = "";

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(HOST + BANCO,
					USUARIO, SENHA);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
