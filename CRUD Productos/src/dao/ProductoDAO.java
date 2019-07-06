package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import model.Producto;
import model.Categoria;
import model.Conexion;

public class ProductoDAO {
	private Conexion con;
	private Connection connection;
	private Client client;
	private WebTarget target;
 
	public ProductoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
		client = ClientBuilder.newClient();
	}
	
	public boolean ValidacionLetras(String cad) {
		boolean num = false;
		boolean str = false;
		boolean ex = false;
		for(int i = 0;i<cad.length();i++) {
			if(Character.isDigit(cad.charAt(i)) == true) {
				num = true;
			}else if (Character.isLetter(cad.charAt(i)) == true){
				str = true;
			}else { 
				ex = true;
			}
			
		}		
		if (num == false && str == true && ex == false) {
			return true;
		}else {
			return false;
		}
	}
 
	public boolean insertar(Producto producto) throws SQLException {		
		boolean registrar = false;
		if (!(producto.getNombre().equals("") || producto.getCategoria() == 0 || ValidacionLetras(producto.getNombre()) == false)) {
			target = client.target("http://ventas-crud-services.herokuapp.com/AniadirProducto?nombre=" + producto.getNombre() + 
																						"&categoria=" + producto.getCategoria() +
																						"&codigo=" + producto.getCodigo());
			
			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			//TODO Verify if the product is on db and set the value of registrar accordingly
			registrar = true;
		}
		return registrar;
	}
	
	public List<Categoria> listarCategorias() throws SQLException{
		List<Categoria> categorias = new ArrayList<Categoria>();
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarCategorias");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		categorias = gson.fromJson(response, new GenericType<List<Categoria>>(){}.getType());
		return categorias;
	}
 
	public List<Producto> listarProductos() throws SQLException {
		List<Producto> productos = new ArrayList<Producto>();
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarProductos");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		productos = gson.fromJson(response, new GenericType<List<Producto>>(){}.getType());
		return productos;
	}
 
	public Producto obtenerPorId(int id) throws SQLException {
		Producto producto = null;
		target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerProducto?id=" + id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		producto = gson.fromJson(response, Producto.class); 
		return producto;
	}
	
	public Producto obtenerPorCodigo(String codigo) throws SQLException {
		Producto producto = null;
 
		String sql = "SELECT * FROM producto WHERE codigo= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, codigo);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			producto = new Producto(res.getInt("id"),  res.getString("nombre"),
					res.getInt("idCategoria"),res.getString("codigo"));
		}
		res.close();
		con.desconectar();
 
		return producto;
	}
 
	public boolean actualizar(Producto producto) throws SQLException {
		boolean rowActualizar = false;
		if (!(producto.getNombre().equals("") || producto.getCategoria() == 0 || ValidacionLetras(producto.getNombre()) == false)) {
			target = client.target("http://ventas-crud-services.herokuapp.com/ActualizarProducto?id=" + producto.getId() +
																						"&nombre=" + producto.getNombre() + 
																						"&categoria=" + producto.getCategoria() +
																						"&codigo=" + producto.getCodigo());
			
			Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			//TODO Verify if the product updates on db and set the value of rowActualizar accordingly
			rowActualizar = true;
		}
		return rowActualizar;
	}
	
	public boolean eliminar(Producto producto) throws SQLException {
		boolean rowEliminar = false;
		String id = Integer.toString(producto.getId());
		target = client.target("http://ventas-crud-services.herokuapp.com/BorrarProducto?id=" + id);
		String response = target.request().delete(String.class);

		//TODO Verify if the product is not on db and set the value of rowEliminar accordingly
		rowEliminar = true;
		return rowEliminar;
	}
}