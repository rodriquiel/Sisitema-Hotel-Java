package controller;

import java.sql.Date;
import java.util.List;

import dao.ReservaDAO;
import modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
        var factory = new factory.ConnectionFactory();
        this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
    }
	
	public List<Reserva> listar() {
        return reservaDAO.listar();
    }

    public void guardar(Reserva reserva) {
        reservaDAO.guardar(reserva);
    }
    
    public int editar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
    	return reservaDAO.editar(id, fechaE, fechaS, valor, formaPago);
    }
    
    public int eliminar(Integer id) {
    	return reservaDAO.eliminar(id);
    }

}
