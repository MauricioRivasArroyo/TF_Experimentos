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
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
 
	public boolean insertar(Cliente cliente) throws SQLException {
		boolean registrar = false;
		String sql = "INSERT INTO cliente values (NULL,'"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"')";
		try {
			connection = con.conectar();	
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
			Cliente cliente = new Cliente(id, cedula, nombre, apellido);
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
					res.getString("apellido"));
		}
		res.close();
		con.desconectar();
 
		return cliente;
	}
 
	// actualizar
	public boolean actualizar(Cliente cliente) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE cliente SET cedula=?,nombre=?,apellido=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getCedula());
		statement.setString(2, cliente.getNombre());
		statement.setString(3, cliente.getApellido());
		statement.setInt(4, cliente.getId());
 
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