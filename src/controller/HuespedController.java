package controller;

import java.sql.Date;
import java.util.List;

import dao.HuespedDAO;
import modelo.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
        var factory = new factory.ConnectionFactory();
        this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
    }
	
	public List<Huesped> listar() {
        return huespedDAO.listar();
    }

    public void guardar(Huesped huesped) {
        huespedDAO.guardar(huesped);
    }
    
    public int editar(Integer id, String nombre, String apellido, Date fechaNac, String nacionalidad, String telefono, Integer idReserva) {
    	return huespedDAO.editar(id, nombre, apellido, fechaNac, nacionalidad, telefono, idReserva);
    }
    
    public int eliminar(Integer id) {
    	return huespedDAO.eliminar(id);
    }
}
