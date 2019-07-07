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

import model.Cliente;
import model.Conexion;

public class ClienteDAO {
	private Conexion con ;
	private Connection connection;
	private Client client;
	private WebTarget target;
 
	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		Conexion.setJdbcURL(jdbcURL);
		Conexion.setJdbcUsername(jdbcUsername);
		Conexion.setJdbcPassword(jdbcPassword);	 
		con = Conexion.getSubFactory(Conexion.MySql);
		
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
		if(cad.length()-1 > min && cad.length()-1 < max ) {
			return true;
		}else {
			return false;
		}
	}
 
	public boolean insertar(Cliente cliente) throws SQLException {
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
	
	public Cliente insertar2(Cliente cliente) throws SQLException {		
		
		String sql = "INSERT INTO cliente values (NULL,'"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"','"+cliente.getGenero()+"','"+cliente.getCategoria()+"','"+cliente.getCorreo()+"')";
		try {
			con.conectar();
			connection = con.getJdbcConnection();
			Statement stm= connection.createStatement();
			stm.execute(sql);
			stm.close();
			connection.close();			
		}catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
		}
		return cliente;
	}
		
	
	public List<Cliente> listarClientes() throws SQLException {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		target = client.target("http://ventas-crud-services.herokuapp.com/ListarClientes");
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		listaCliente = gson.fromJson(response, new GenericType<List<Cliente>>(){}.getType());
		return listaCliente;
	}
 
	public Cliente obtenerPorId(int id) throws SQLException {
		Cliente cliente = null;
		target = client.target("http://ventas-crud-services.herokuapp.com/ObtenerCliente?id=" + id);
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		Gson gson = new Gson();
		cliente = gson.fromJson(response, Cliente.class); 
		return cliente;
	}
	public Cliente obtenerPorCedula(String cedula) throws SQLException {
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
	public boolean actualizar(Cliente cliente) throws SQLException {
		boolean rowActualizar = false;
		Cliente clienteDB = obtenerPorId(cliente.getId());
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
			
			clienteDB = obtenerPorId(clienteDB.getId());
			if (clienteDB.getNombre().equals(cliente.getNombre())) {
				rowActualizar = true;
			}
		}
		return rowActualizar;
	}
	
	public boolean eliminar(Cliente cliente) throws SQLException {
		boolean rowEliminar = false;
		String id = Integer.toString(cliente.getId());
		target = client.target("http://ventas-crud-services.herokuapp.com/BorrarCliente?id=" + id);
		String response = target.request().delete(String.class);
		if (!response.isEmpty()) {
			rowEliminar = true;			
		}
		return rowEliminar;
	}
	public boolean eliminarPorCedula(Cliente cliente) throws SQLException {
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
}