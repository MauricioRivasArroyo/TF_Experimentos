package com.ventas.services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

	private Conexion con = new Conexion();
	private Connection connection;	
    public Controller(){
    	
    }
    @GetMapping(value = "/ListarClientes")
    public ResponseEntity listarClientes() throws SQLException, URISyntaxException, ClassNotFoundException {
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
        return ResponseEntity.ok(listaCliente);
    }
    
    @GetMapping(value = "/ListarProductos")
    public ResponseEntity listarProductos() throws SQLException, URISyntaxException, ClassNotFoundException {
    	List<Producto> listaProducto = new ArrayList<Producto>();
		String sql = "SELECT * FROM producto";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String nombre = resulSet.getString("nombre");
			int categoria = resulSet.getInt("categoria");
			String codigo = resulSet.getString("codigo");
			Producto producto = new Producto(id, nombre, categoria,codigo);
			listaProducto.add(producto);
		}
		con.desconectar();
        return ResponseEntity.ok(listaProducto);
    }
    
    @GetMapping(value = "/ListarCategorias")
    public ResponseEntity listarCategorias() throws SQLException, URISyntaxException, ClassNotFoundException {
    	List<Categoria> listaCategoria = new ArrayList<Categoria>();
		String sql = "SELECT * FROM categoria";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String nombre = resulSet.getString("nombre");
			Categoria categoria = new Categoria(id, nombre);
			listaCategoria.add(categoria);
		}
		con.desconectar();
        return ResponseEntity.ok(listaCategoria);
    }

    public Cliente obtenerClientePorId(int id) throws SQLException, URISyntaxException, ClassNotFoundException {
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
    public Producto obtenerProductoPorId(int id) throws SQLException, URISyntaxException, ClassNotFoundException {
    	Producto producto = null; 
		String sql = "SELECT * FROM producto WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			producto = new Producto(res.getInt("id"), res.getString("nombre"),
					res.getInt("categoria"),res.getString("codigo"));
		}
		res.close();
		con.desconectar();
 
		return producto;
	}
    public Categoria obtenerCategoriaPorId(int id) throws SQLException, URISyntaxException, ClassNotFoundException {
    	Categoria categoria = null; 
		String sql = "SELECT * FROM categoria WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			categoria = new Categoria(res.getInt("id"),  res.getString("nombre"));
		}
		res.close();
		con.desconectar();
 
		return categoria;
	}
    @GetMapping(value = "/ObtenerCliente")
    public ResponseEntity getCliente(@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {   	
    	Cliente cliente = obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }
    
    @GetMapping(value = "/ObtenerProducto")
    public ResponseEntity getProducto(@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {   	
    	Producto producto = obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }
    
    @GetMapping(value = "/ObtenerCategoria")
    public ResponseEntity getCategoria(@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {   	
    	Categoria categoria = obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping(value = "/AñadirCliente")
    public ResponseEntity añadirCliente(@RequestParam(value="nombre") String nombre,@RequestParam(value="cedula") String cedula,@RequestParam(value="apellido") String apellido,
    		@RequestParam(value="genero") String genero,@RequestParam(value="categoria") String categoria,@RequestParam(value="correo") String correo) throws URISyntaxException, ClassNotFoundException {
		Cliente cliente = new Cliente(0,cedula,nombre,apellido,genero,categoria,correo);
		
		String sql = "INSERT INTO cliente values (NULL,'"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getApellido()+"','"+cliente.getGenero()+"','"+cliente.getCategoria()+"','"+cliente.getCorreo()+"')";
		try {
			con.conectar();
			connection = con.getJdbcConnection();
			Statement stm= connection.createStatement();
			stm.execute(sql);
			stm.close();
			connection.close();			
			;
			} catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
			}
		
       return ResponseEntity.ok(cliente);
    }
    
    @PostMapping(value = "/AñadirProducto")
    public ResponseEntity añadirProducto(@RequestParam(value="nombre") String nombre,@RequestParam(value="categoria") int categoria,@RequestParam(value="codigo") String codigo ) throws URISyntaxException, ClassNotFoundException {
    	Producto producto = new Producto(0,nombre,categoria,codigo);
		
		String sql = "INSERT INTO producto values (NULL,'"+producto.getNombre()+"','"+producto.getCategoria()+"','"+producto.getCodigo()+"')";
		try {
			con.conectar();
			connection = con.getJdbcConnection();
			Statement stm= connection.createStatement();
			stm.execute(sql);
			stm.close();
			connection.close();			
			;
			} catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
			}
		
       return ResponseEntity.ok(producto);
    }
    
    @PostMapping(value = "/AñadirCategoria")
    public ResponseEntity añadirCategoria(@RequestParam(value="nombre") String nombre) throws URISyntaxException, ClassNotFoundException {
    	Categoria categoria = new Categoria(0,nombre);
		
		String sql = "INSERT INTO categoria values (NULL,'"+categoria.getNombre()+"')";
		try {
			con.conectar();
			connection = con.getJdbcConnection();
			Statement stm= connection.createStatement();
			stm.execute(sql);
			stm.close();
			connection.close();			
			;
			} catch (SQLException e) {
				System.out.println("Error: método registrar");
				e.printStackTrace();
			}
		
       return ResponseEntity.ok(categoria);
    }

    @PutMapping(value = "/ActualizarCliente")
    public ResponseEntity actualizarCliente(@RequestParam(value="cedula") String cedula,@RequestParam(value="nombre") String nombre,@RequestParam(value="apellido") String apellido
    		,@RequestParam(value="genero") String genero,@RequestParam(value="categoria") String categoria,@RequestParam(value="correo") String correo,@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {
    	Cliente cliente = new Cliente(id,cedula,nombre,apellido,genero,categoria,correo); 	
    	String sql = "UPDATE cliente SET cedula=?,nombre=?,apellido=?,genero=?,categoria_cliente=?,correo=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getNombre());
		statement.setString(2, cliente.getApellido());
		statement.setString(3, cliente.getGenero());
		statement.setString(4, cliente.getCategoria());
		statement.setString(5, cliente.getCorreo());
		statement.setInt(6, cliente.getId()); 
		statement.close();
		con.desconectar();
        return ResponseEntity.ok(cliente);
    }
    
    @PutMapping(value = "/ActualizarProducto")
    public ResponseEntity actualizarProducto(@RequestParam(value="id") int id,@RequestParam(value="nombre") String nombre,@RequestParam(value="categoria") int categoria,@RequestParam(value="codigo") String codigo ) throws URISyntaxException, ClassNotFoundException, SQLException {
    	Producto producto= new Producto(id,nombre,categoria,codigo); 	
    	String sql = "UPDATE producto SET nombre=?,categoria=?,codigo=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, producto.getNombre());
		statement.setInt(2, producto.getCategoria());
		statement.setString(3, producto.getCodigo());
		statement.setInt(4, producto.getId()); 
		statement.close();
		con.desconectar();
        return ResponseEntity.ok(producto);
    }
    
    @PutMapping(value = "/ActualizarCategoria")
    public ResponseEntity actualizarCategoria(@RequestParam(value="id") int id,@RequestParam(value="nombre") String nombre) throws SQLException, URISyntaxException, ClassNotFoundException {
    	Categoria  categoria = new Categoria(id,nombre); 	
    	String sql = "UPDATE categoria SET nombre=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, categoria.getNombre());
		statement.setInt(2, categoria.getId()); 
		statement.close();
		con.desconectar();
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping(value = "/BorrarCliente")
    public ResponseEntity borrarCliente(@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {
    	 Cliente cliente = obtenerClientePorId(id);
    	String sql = "DELETE FROM cliente WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		statement.close();
		con.desconectar();
        return ResponseEntity.ok(cliente);
    }
    
    @DeleteMapping(value = "/BorrarProducto")
    public ResponseEntity borrarProducto(@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {
    	Producto producto= obtenerProductoPorId(id);
    	String sql = "DELETE FROM producto WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		statement.close();
		con.desconectar();
        return ResponseEntity.ok(producto);
    }
    
    @DeleteMapping(value = "/BorrarCategoria")
    public ResponseEntity borrarCategoria(@RequestParam(value="id") int id) throws SQLException, URISyntaxException, ClassNotFoundException {
    	Categoria categoria = obtenerCategoriaPorId(id);
    	String sql = "DELETE FROM categoria WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		statement.close();
		con.desconectar();
        return ResponseEntity.ok(categoria);
    }
}