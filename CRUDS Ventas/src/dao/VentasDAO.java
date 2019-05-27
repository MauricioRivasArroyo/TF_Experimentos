package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Cliente;
import model.Conexion;
import model.Producto;
import model.Usuario;

public class VentasDAO {
	private Conexion con;
	private Connection connection;
 
	public VentasDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
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
	
	public boolean Validar_usuario(Usuario usuario) throws SQLException {
			String sql = "SELECT * FROM usuario WHERE nombre=? and contraseña=?";
			con.conectar();
			connection = con.getJdbcConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getContraseña());
			ResultSet res = statement.executeQuery();	
			while(res.next())
			{
				return true;
			}
			res.close();
			con.desconectar();
			return false;		
		

	}
	public boolean insertar_cliente(Cliente cliente) throws SQLException {		
		boolean registrar = false;
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
	public boolean insertar_categoria(Categoria categoria) throws SQLException {		
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
	public boolean insertar_producto(Producto producto) throws SQLException {		
		boolean registrar = false;
		if(producto.getNombre().equals("") || producto.getCategoria() == 0 || ValidacionLetras(producto.getNombre()) == false) {
			return registrar;
		}else {
		String sql = "INSERT INTO producto values (NULL,'"+producto.getNombre()+"','"+producto.getCategoria()+"','"+producto.getCodigo()+"')";
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
			String codigo = resulSet.getString("codigo");
			Producto producto = new Producto(id, nombre, categoria,codigo);
			listaProducto.add(producto);
		}
		con.desconectar();
		return listaProducto;
	}public Cliente obtenerPorId_cliente(int id) throws SQLException {
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
	public Categoria obtenerPorId_categoria(int id) throws SQLException {
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
	public Producto obtenerPorId_producto(int id) throws SQLException {
		Producto producto = null;
 
		String sql = "SELECT * FROM producto WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			producto = new Producto(res.getInt("id"),  res.getString("nombre"),
					res.getInt("idCategoria"),res.getString("codigo"));
		}
		res.close();
		con.desconectar();
 
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
		if(ValidacionLetras(cliente.getNombre()) == false) {
			return rowActualizar;
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
 
	public boolean actualizar_categoria(Categoria categoria) throws SQLException {
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
	public boolean actualizar_producto(Producto producto) throws SQLException {
		boolean rowActualizar = false;
		if(producto.getNombre().equals("") || producto.getCategoria() == 0 || ValidacionLetras(producto.getNombre()) == false) {
			return rowActualizar;
		}else {
		String sql = "UPDATE producto SET nombre=?,idCategoria=?,codigo=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, producto.getNombre());
		statement.setInt(2, producto.getCategoria());
		statement.setString(3, producto.getCodigo());
		statement.setInt(4, producto.getId());
 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
		}
	}
	public boolean eliminar_cliente(Cliente cliente) throws SQLException {
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
		String sql = "DELETE FROM producto WHERE codigo=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, producto.getCodigo());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
	
}