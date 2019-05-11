package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import model.Categoria;
import model.Conexion;

public class CategoriaDAO {
	private Conexion con;
	private Connection connection;
 
	public CategoriaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
 
	public boolean insertar(Categoria categoria) throws SQLException {		
		boolean registrar = false;
		String sql = "INSERT INTO categoria values (NULL,'"+categoria.getNombre()+"')";

		if (categoria.getNombre().isEmpty() || categoria.getNombre().matches("[0-9]+")) {
			return false;
		}
		try {
			con.conectar();
			connection = con.getJdbcConnection();
			Statement stm= connection.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			connection.close();
			} catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
			}
		return registrar;
	}
 
	public List<Categoria> listarCategorias() throws SQLException {
 
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		String sql = "SELECT * FROM categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String nombre = resulSet.getString("nombre");		
			Categoria categoria =  Categoria.builder().setId(id).setNombre(nombre).build();
			listaCategoria.add(categoria);
		}
		con.desconectar();
		return listaCategoria;
	}
 
	public Categoria obtenerPorId(int id) throws SQLException {
		Categoria categoria = null;
 
		String sql = "SELECT * FROM categoria WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			categoria =  Categoria.builder().setId(res.getInt("id")).setNombre(res.getString("nombre")).build();
		}
		res.close();
		con.desconectar();
 
		return categoria;
	}
	
	public Categoria obtenerPorNombre(Categoria categoria) throws SQLException {
 
		String sql = "SELECT * FROM categoria WHERE nombre= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, categoria.getNombre());
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			categoria =  Categoria.builder().setId(res.getInt("id")).setNombre(res.getString("nombre")).build();
		}
		res.close();
		con.desconectar();
 
		return categoria;
	}
 
	// actualizar
	public boolean actualizar(Categoria categoria) throws SQLException {
		boolean rowActualizar = false;
		if (categoria.getNombre().isEmpty() || categoria.getNombre().matches("[0-9]+")) {
			return false;
		}
		String sql = "UPDATE categoria SET nombre=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);		
		statement.setString(1, categoria.getNombre());
		statement.setInt(2, categoria.getId());
 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	public boolean eliminar(Categoria categoria) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM categoria WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, categoria.getId());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
	
	public boolean eliminarPorNombre(Categoria categoria) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM categoria WHERE nombre=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, categoria.getNombre());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}

}