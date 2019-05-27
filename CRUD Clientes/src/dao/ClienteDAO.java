package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import model.Cliente;
import model.Conexion;

public class ClienteDAO {
	private Conexion con ;
	private Connection connection;
 
	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
	Conexion.setJdbcURL(jdbcURL);
	Conexion.setJdbcUsername(jdbcUsername);
	Conexion.setJdbcPassword(jdbcPassword);	 
     con = Conexion.getSubFactory(Conexion.MySql);
		
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
		if (cliente == null) { 
			return registrar;
		}else {
		if((cliente.getCedula().equals("") || cliente.getNombre().equals("") || cliente.getApellido().equals("") || cliente.getGenero().equals(""))  || 
				(ValidacionLetras(cliente.getNombre())== false || ValidacionLetras(cliente.getApellido())== false)) {	
			return registrar;
				}else {
		
		String sql = "INSERT INTO cliente values (NULL,'"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"','"+cliente.getGenero()+"','"+cliente.getCategoria()+"','"+cliente.getCorreo()+"')";
		try {
			con.conectar();
			connection = con.getJdbcConnection();
			Statement stm= connection.createStatement();
			stm.execute(sql);
			stm.close();
			connection.close();			
			registrar=true;
			} catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
			}
		return registrar;
		}
		}
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
			} catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
			}
		return cliente;
		}
		
	
	public List<Cliente> listarClientes() throws SQLException {
 
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		String sql = "SELECT * FROM cliente";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String cedula = resulSet.getString("cedula");
			String nombre = resulSet.getString("nombre");
			String apellido = resulSet.getString("apellido");
			String genero = resulSet.getString("genero");
			String categoria = resulSet.getString("categoria_cliente");
			String correo = resulSet.getString("correo");
			Cliente cliente = new Cliente(id, cedula, nombre, apellido,genero,categoria,correo);
			listaCliente.add(cliente);
		}
		con.desconectar();
		return listaCliente;
	}
 
	public Cliente obtenerPorId(int id) throws SQLException {
		Cliente cliente = null;
 
		String sql = "SELECT * FROM cliente WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			cliente = new Cliente(res.getInt("id"), res.getString("cedula"), res.getString("nombre"),
					res.getString("apellido"),res.getString("genero"),res.getString("categoria_cliente"),res.getString("correo"));
		}
		res.close();
		con.desconectar();
 
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
		Cliente clienteBD = obtenerPorCedula(cliente.getCedula());
		if((clienteBD.getCedula().equals(cliente.getCedula()) && clienteBD.getNombre().equals(cliente.getNombre()) 
				&& clienteBD.getApellido().equals(cliente.getApellido()) && clienteBD.getCorreo().equals(cliente.getCorreo()) 
				&& clienteBD.getGenero().equals(cliente.getGenero()) && clienteBD.getCategoria().equals(cliente.getCategoria())
				)|| ValidacionLetras(cliente.getNombre()) == false) {
			return true;
		}else {
		String sql = "UPDATE cliente SET nombre=?,apellido=?,genero=?,categoria_cliente=?,correo=? WHERE cedula=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getNombre());
		statement.setString(2, cliente.getApellido());
		statement.setString(3, cliente.getGenero());
		statement.setString(4, cliente.getCategoria());
		statement.setString(5, cliente.getCorreo());
		statement.setString(6, cliente.getCedula()); 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
		}
	}
	
	public boolean eliminar(Cliente cliente) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM cliente WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cliente.getId());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
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