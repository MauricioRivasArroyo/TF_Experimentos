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
	private Conexion con;
	private Connection connection;
 
	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
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
		if((cliente.getCedula().equals("") || cliente.getNombre().equals("") || cliente.getApellido().equals("") || cliente.getGenero().equals(""))  || 
				(ValidacionLetras(cliente.getNombre())== true || ValidacionLetras(cliente.getApellido())== true)) {	
			return registrar;
				}else {
		
		String sql = "INSERT INTO cliente values (NULL,'"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"','"+cliente.getGenero()+"','"+cliente.getCategoria()+"','"+cliente.getCorreo()+"')";
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
 
	// actualizar
	public boolean actualizar(Cliente cliente) throws SQLException {
		boolean rowActualizar = false;
		
		String sql = "UPDATE cliente SET cedula=?,nombre=?,apellido=?,genero=?,categoria_cliente=?,correo=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getCedula());
		statement.setString(2, cliente.getNombre());
		statement.setString(3, cliente.getApellido());
		statement.setString(4, cliente.getGenero());
		statement.setString(5, cliente.getCategoria());
		statement.setString(6, cliente.getCorreo());
		statement.setInt(7, cliente.getId());
		
 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
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
}