package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import model.Categoria;
import model.Conexion;

public class CategoriaDAO {
	private Conexion con;
	private Connection connection;
	private Client client;
	private WebTarget target;
 
	public CategoriaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
		client = ClientBuilder.newClient();
	}
 
	public boolean insertar(Categoria categoria) throws SQLException {		
		boolean registrar = false;
		if (!(categoria.getNombre().isEmpty() || categoria.getNombre().matches("[0-9]+"))) {
			target = client.target("http://ventas-crud-services.herokuapp.com/AniadirCategoria?nombre=" + categoria.getNombre());

			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			registrar = true;
		}
		return registrar;
	}
 
	public List<Categoria> listarCategorias() throws SQLException {
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarCategorias");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		listaCategoria = gson.fromJson(response, new GenericType<List<Categoria>>(){}.getType());
				
		return listaCategoria;
	}
 
	public Categoria obtenerPorId(int id) throws SQLException {
		Categoria categoria = null;

		target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerCategoria?id=" + id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		categoria = gson.fromJson(response, Categoria.class); 
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
 
	public boolean actualizar(Categoria categoria) throws SQLException {
		boolean rowActualizar = false;

		Categoria categoriaDB = new Categoria();
		if (!(categoria.getNombre().isEmpty() || categoria.getNombre().matches("[0-9]+"))) {
			target = client.target("http://ventas-crud-services.herokuapp.com/ActualizarCategoria?id=" + categoria.getId() + "&nombre=" + categoria.getNombre());
			Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			
			categoriaDB = obtenerPorId(categoria.getId());
			if (categoriaDB.getNombre().equals(categoria.getNombre())) {
				rowActualizar = true;
			}
		}
		return rowActualizar;
	}
	
	public boolean eliminar(Categoria categoria) throws SQLException {
		boolean rowEliminar = false;
		String id = Integer.toString(categoria.getId());
		target = client.target("http://ventas-crud-services.herokuapp.com/BorrarCategoria?id=" + id);
		String response = target.request().delete(String.class);
		if (!response.isEmpty()) {
			rowEliminar = true;			
		} 
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

	public boolean eliminarTodos() throws SQLException{
		boolean rowEliminar = false;
		String sql = "DELETE FROM categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
	
}