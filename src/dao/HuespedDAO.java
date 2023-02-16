package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;

public class HuespedDAO {

	private Connection connection;
	
	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Huesped huesped) {
		String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nac, nacionalidad, telefono, id_reserva) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, huesped.getNombre());
			pstm.setString(2, huesped.getApellido());
			pstm.setDate(3, huesped.getFechaNac());
			pstm.setString(4, huesped.getNacionalidad());
			pstm.setString(5, huesped.getTelefono());
			pstm.setInt(6, huesped.getIdReserva());
			
			pstm.execute();
			
			final ResultSet resultSet = pstm.getGeneratedKeys();
			
			try(resultSet){
				while (resultSet.next()) {
                    huesped.setId(resultSet.getInt(1));
                    
                    System.out.println(String.format("Fue insertado el producto: ", huesped.getId()));
                }
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> listar(){
		List<Huesped> resultado = new ArrayList<Huesped>();
		
		String sql = "SELECT id, nombre, apellido, fecha_nac, nacionalidad, telefono, id_reserva FROM huespedes";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			 pstm.execute();
			    
             final ResultSet resultSet = pstm.getResultSet();
             
             try(resultSet){
            	 while (resultSet.next()) {
                     resultado.add(new Huesped(
                    		 resultSet.getInt("id"),
                    		 resultSet.getString("nombre"),
                    		 resultSet.getString("apellido"),
                    		 resultSet.getDate("fecha_nac"),
                    		 resultSet.getString("nacionalidad"),
                    		 resultSet.getString("telefono"),
                    		 resultSet.getInt("id_reserva")
                    		 ));
            	 }
             }     
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	
	public int eliminar(Integer id) {
		
		String sql = "DELETE FROM HUESPEDES WHERE ID = ?";
		
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
	
	public int editar(Integer id, String nombre, String apellido, Date fechaNac, String nacionalidad, String telefono, Integer idReserva) {
		
		String sql = "UPDATE HUESPEDES SET "
				+ "nombre = ?, "
				+ "apellido = ?, "
				+ "fecha_nac = ?, "
				+ "nacionalidad = ?, "
				+ "telefono = ?, "
				+ "id_reserva = ? "
				+ "WHERE id = ?";
		
		try {
			final PreparedStatement pstm = connection.prepareStatement(sql);
			
			try (pstm) {
				pstm.setString(1, nombre);
				pstm.setString(2, apellido);
				pstm.setDate(3, fechaNac);
				pstm.setString(4, nacionalidad);
				pstm.setString(5, telefono);
				pstm.setInt(6, idReserva);
				
				pstm.execute();
				
				int updateCount = pstm.getUpdateCount();
				
				return updateCount;	
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
