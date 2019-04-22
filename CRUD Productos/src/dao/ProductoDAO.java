package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import model.Producto;
import model.Categoria;
import model.Conexion;

public class ProductoDAO {
	private Conexion con;
	private Connection connection;
 
	public ProductoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
 
	public boolean insertar(Producto producto) throws SQLException {		
		boolean registrar = false;
		String sql = "INSERT INTO producto values (NULL,'"+producto.getNombre()+"','"+producto.getCategoria()+"')";
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
	public List<Categoria> listarCategorias() throws SQLException{
		List<Categoria> listaCategoria = new ArrayList<Categoria>();
		String sql = "SELECT * FROM categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String nombre = resulSet.getString("nombre");
			Categoria categoria = new Categoria(id, nombre );
			listaCategoria.add(categoria);
		}
		con.desconectar();
		return listaCategoria;
	}
 
	public List<Producto> listarProductos() throws SQLException {
 
		List<Producto> listaProducto = new ArrayList<Producto>();
		String sql = "SELECT * FROM producto";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String nombre = resulSet.getString("nombre");
			int categoria = resulSet.getInt("idCategoria");
			Producto producto = new Producto(id, nombre, categoria);
			listaProducto.add(producto);
		}
		con.desconectar();
		return listaProducto;
	}
 
	public Producto obtenerPorId(int id) throws SQLException {
		Producto producto = null;
 
		String sql = "SELECT * FROM producto WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			producto = new Producto(res.getInt("id"),  res.getString("nombre"),
					res.getInt("idCategoria"));
		}
		res.close();
		con.desconectar();
 
		return producto;
	}
 
	// actualizar
	public boolean actualizar(Producto producto) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE producto SET nombre=?,idCategoria=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, producto.getNombre());
		statement.setInt(2, producto.getCategoria());
		statement.setInt(3, producto.getId());
 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	public boolean eliminar(Producto producto) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM producto WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, producto.getId());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
}