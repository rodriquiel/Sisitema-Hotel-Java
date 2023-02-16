package controller;

import dao.UsuarioDAO;

public class UsuarioController {
	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
        var factory = new factory.ConnectionFactory();
        this.usuarioDAO = new UsuarioDAO(factory.recuperaConexion());
	}
}
