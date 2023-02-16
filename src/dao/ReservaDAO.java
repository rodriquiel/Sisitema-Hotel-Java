package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	/*public void cerrarConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	
	public void guardar(Reserva reserva) {
		String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) "
				+ "VALUES (?, ?, ?, ?)";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setDate(1, reserva.getFechaE());
			pstm.setDate(2, reserva.getFechaS());
			pstm.setString(3, reserva.getValor());
			pstm.setString(4, reserva.getFormaPago());
			
			pstm.execute();
			
			final ResultSet resultSet = pstm.getGeneratedKeys();
			
			try(resultSet){
				while (resultSet.next()) {
                    reserva.setId(resultSet.getInt(1));
                    
                    System.out.println(String.format("Fue insertado el producto: ", reserva.getId()));
                }
			}
				
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> listar(){
		List<Reserva> resultado = new ArrayList<Reserva>();
		
		String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			 pstm.execute();
			    
             final ResultSet resultSet = pstm.getResultSet();
             
             try(resultSet){
            	 while (resultSet.next()) {
                     resultado.add(new Reserva(
                    		 resultSet.getInt("id"),
                    		 resultSet.getDate("fecha_entrada"),
                    		 resultSet.getDate("fecha_salida"),
                    		 resultSet.getString("valor"),
                    		 resultSet.getString("forma_pago")));
            	 }
             }     
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	
	public int eliminar(Integer id) {
		
		String sql = "DELETE FROM RESERVAS WHERE ID = ?";
		
		try {
			final PreparedStatement pstm = connection.prepareStatement(sql);
			
			try (pstm){
				pstm.setInt(1, id);
				pstm.execute();
				
				int updateCount = pstm.getUpdateCount();
				
				return updateCount;
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public int editar(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
		
		String sql = "UPDATE RESERVAS SET "
				+ "fecha_entrada = ?, "
				+ "fecha_salida = ?, "
				+ "valor = ?, "
				+ "forma_pago = ? "
				+ "WHERE id = ?";
		
		try {
			final PreparedStatement pstm = connection.prepareStatement(sql);
			
			try (pstm) {
				pstm.setDate(1, fechaE);
				pstm.setDate(2, fechaS);
				pstm.setString(3, valor);
				pstm.setString(4, formaPago);
				pstm.setInt(5, id);
				
				pstm.execute();
				
				int updateCount = pstm.getUpdateCount();
				
				return updateCount;	
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
