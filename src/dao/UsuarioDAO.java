package dao;

import java.sql.Connection;

public class UsuarioDAO {
	private Connection connection;
	
	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}
	
}
