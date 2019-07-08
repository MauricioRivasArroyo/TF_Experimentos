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
import model.Cliente;
import model.Conexion;
import model.Producto;
import model.Usuario;

public class VentasDAO {
	private Conexion con;
	private Connection connection;
	private Client client;
	private WebTarget target;

	public VentasDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
		client = ClientBuilder.newClient();

	}	
	public boolean ValidacionNumeros(String cad) {
		int num;
		try {
			num = Integer.parseInt(cad);
			return true;
		}catch(Exception e) {
			return false;
		}	
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
	public boolean ValidacionAlfanumerico(String cad) {
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
		if (num == true && str == true && ex == false) {
			return true;
		}else {
			return false;
		}
	}
	public boolean ValidacionLimite(String cad, int min, int max){
		if(cad.length()-1 >= min && cad.length()-1 <= max ) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean Validar_usuario(Usuario usuario) throws SQLException {
			Usuario usuario_response = null;
			target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerUsuario?nombre=" + usuario.getNombre() +"&contrasenia=" + usuario.getContraseña());
			String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
			Gson gson = new Gson();
			usuario_response = gson.fromJson(response, Usuario.class); 
			if(usuario_response != null)
			{
				return true;
			}else {
				return false;
			}			
				
		

	}
	public boolean insertar_usuario(Usuario usuario) throws SQLException {		
		boolean registrar = false;
		if (usuario != null) { 
			if(ValidacionAlfanumerico(usuario.getNombre())== true || ValidacionAlfanumerico(usuario.getContraseña())== true) {	
				target = client.target("http://ventas-crud-services.herokuapp.com/AniadirUsuario?username=" + usuario.getNombre() +
																								"&password=" + usuario.getContraseña());
				
				Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new String(), MediaType.APPLICATION_JSON));
				registrar = true;
			}
		}
		return registrar;	
	}
	
	public boolean insertar_cliente(Cliente cliente) throws SQLException {		
		boolean registrar = false;
		if (cliente != null) { 
			if(!((cliente.getCedula().equals("") || cliente.getNombre().equals("") || cliente.getApellido().equals("") || cliente.getGenero().equals(""))  || 
					(ValidacionLetras(cliente.getNombre())== false || ValidacionLetras(cliente.getApellido())== false))) {	
				target = client.target("http://ventas-crud-services.herokuapp.com/AniadirCliente?nombre=" + cliente.getNombre() +
																								"&cedula=" + cliente.getCedula() +
																								"&apellido=" + cliente.getApellido() + 
																								"&genero=" + cliente.getGenero() + 
																								"&categoria=" + cliente.getCategoria() +
																								"&correo=" + cliente.getCorreo());
				
				Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new String(), MediaType.APPLICATION_JSON));
				registrar = true;
			}
		}
		return registrar;	
	}
	public boolean insertar_categoria(Categoria categoria) throws SQLException {		
		boolean registrar = false;
		if (!(categoria.getNombre().isEmpty() || categoria.getNombre().matches("[0-9]+"))) {
			target = client.target("http://ventas-crud-services.herokuapp.com/AniadirCategoria?nombre=" + categoria.getNombre());

			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			registrar = true;
		}
		return registrar;
	}
	public boolean insertar_producto(Producto producto) throws SQLException {		
		boolean registrar = false;
		if (!(producto.getNombre().equals("") || producto.getCategoria() == 0 || ValidacionLetras(producto.getNombre()) == false)) {
			target = client.target("http://ventas-crud-services.herokuapp.com/AniadirProducto?nombre=" + producto.getNombre() + 
																						"&categoria=" + producto.getCategoria() +
																						"&codigo=" + producto.getCodigo());
			
			Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			registrar = true;
		}
		return registrar;
	}
	public List<Cliente> listarClientes() throws SQLException {
		 
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarClientes");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		listaCliente = gson.fromJson(response, new GenericType<List<Cliente>>(){}.getType());
		return listaCliente;
	}
	public List<Categoria> listarCategorias() throws SQLException {
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarCategorias");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		listaCategoria = gson.fromJson(response, new GenericType<List<Categoria>>(){}.getType());
				
		return listaCategoria;
	}
	public List<Producto> listarProductos() throws SQLException {
		List<Producto> productos = new ArrayList<Producto>();
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarProductos");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		productos = gson.fromJson(response, new GenericType<List<Producto>>(){}.getType());
		return productos;
	}
	public Cliente obtenerPorId_cliente(int id) throws SQLException {
		Cliente cliente = null;
		target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerCliente?id=" + id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		cliente = gson.fromJson(response, Cliente.class); 
		return cliente;
	} 
	public Categoria obtenerPorId_categoria(int id) throws SQLException {
		Categoria categoria = null;

		target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerCategoria?id=" + id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		categoria = gson.fromJson(response, Categoria.class); 
		return categoria;
	}
	public Producto obtenerPorId_producto(int id) throws SQLException {
		Producto producto = null;
		target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerProducto?id=" + id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		producto = gson.fromJson(response, Producto.class); 
		return producto;
	}
	public Cliente obtenerPorCedula_cliente(String cedula) throws SQLException {
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE cedula= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cedula); 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			cliente = new Cliente(res.getInt("id"), res.getString("cedula"), res.getString("nombre"),
					res.getString("apellido"),res.getString("genero"),res.getString("categoria_cliente"),res.getString("correo"));
		}
		res.close();
		con.desconectar();
 
		return cliente;
	}
	
	public Categoria obtenerPorNombre_categoria(Categoria categoria) throws SQLException {
 
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
	public Producto obtenerPorCodigo_producto(String codigo) throws SQLException {
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
	public boolean actualizar_cliente(Cliente cliente) throws SQLException {
		boolean rowActualizar = false;
		Cliente clienteDB = obtenerPorId_cliente(cliente.getId());
		if(!((cliente.getCedula().equals("") || cliente.getNombre().equals("") || cliente.getApellido().equals("") || cliente.getGenero().equals(""))  || 
				(ValidacionLetras(cliente.getNombre())== false || ValidacionLetras(cliente.getApellido())== false))) {
			target = client.target("http://ventas-crud-services.herokuapp.com/ActualizarCliente?id=" + cliente.getId() +
																								"&nombre=" + cliente.getNombre() +
																								"&cedula=" + cliente.getCedula() +
																								"&apellido=" + cliente.getApellido() + 
																								"&genero=" + cliente.getGenero() + 
																								"&categoria=" + cliente.getCategoria() +
																								"&correo=" + cliente.getCorreo());
			Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			
			clienteDB = obtenerPorId_cliente(clienteDB.getId());
			if (clienteDB.getNombre().equals(cliente.getNombre())) {
				rowActualizar = true;
			}
		}
		return rowActualizar;
	}
 
	public boolean actualizar_categoria(Categoria categoria) throws SQLException {
		boolean rowActualizar = false;

		Categoria categoriaDB = new Categoria();
		if (!(categoria.getNombre().isEmpty() || categoria.getNombre().matches("[0-9]+"))) {
			target = client.target("http://ventas-crud-services.herokuapp.com/ActualizarCategoria?id=" + categoria.getId() + "&nombre=" + categoria.getNombre());
			Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			
			categoriaDB = obtenerPorId_categoria(categoria.getId());
			if (categoriaDB.getNombre().equals(categoria.getNombre())) {
				rowActualizar = true;
			}
		}
		return rowActualizar;
	}
	public boolean actualizar_producto(Producto producto) throws SQLException {
		boolean rowActualizar = false;
		Producto productoDB = new Producto();
		if (!(producto.getNombre().equals("") || producto.getCategoria() == 0 || ValidacionLetras(producto.getNombre()) == false)) {
			target = client.target("http://ventas-crud-services.herokuapp.com/ActualizarProducto?id=" + producto.getId() +
																						"&nombre=" + producto.getNombre() + 
																						"&categoria=" + producto.getCategoria() +
																						"&codigo=" + producto.getCodigo());
			
			Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(new String(), MediaType.APPLICATION_JSON));
			
			productoDB = obtenerPorId_producto(producto.getId());
			boolean verificaNombre = productoDB.getNombre().equals(producto.getNombre());
			boolean verificaCodigo = productoDB.getCodigo().equals(producto.getCodigo());
			boolean verificaCategoria = productoDB.getCategoria() == producto.getCategoria();
			if (verificaNombre && verificaCodigo && verificaCategoria) {
				rowActualizar = true;
			}
		}
		return rowActualizar;
	}
	public boolean eliminar_cliente(Cliente cliente) throws SQLException {
		boolean rowEliminar = false;
		String id = Integer.toString(cliente.getId());
		target = client.target("http://ventas-crud-services.herokuapp.com/BorrarCliente?id=" + id);
		String response = target.request().delete(String.class);
		if (!response.isEmpty()) {
			rowEliminar = true;			
		}
		return rowEliminar;
	}
	public boolean eliminarPorCedula_cliente(Cliente cliente) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM cliente WHERE cedula=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getCedula());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
	public boolean eliminar_categoria(Categoria categoria) throws SQLException {
		boolean rowEliminar = false;
		String id = Integer.toString(categoria.getId());
		target = client.target("http://ventas-crud-services.herokuapp.com/BorrarCategoria?id=" + id);
		String response = target.request().delete(String.class);
		if (!response.isEmpty()) {
			rowEliminar = true;			
		} 
		return rowEliminar;
	}
	
	public boolean eliminarPorNombre_categoria(Categoria categoria) throws SQLException {
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

	public boolean eliminarTodos_categoria() throws SQLException{
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
	public boolean eliminar_producto(Producto producto) throws SQLException {
		boolean rowEliminar = false;
		String id = Integer.toString(producto.getId());
		target = client.target("http://ventas-crud-services.herokuapp.com/BorrarProducto?id=" + id);
		String response = target.request().delete(String.class);
		if (!response.isEmpty()) {
			rowEliminar = true;			
		}
		return rowEliminar;
	}
	
}