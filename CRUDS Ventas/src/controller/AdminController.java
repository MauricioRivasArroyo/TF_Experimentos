package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VentasDAO;
import model.*;


@WebServlet("/adminController")
public class AdminController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	VentasDAO ventasDAO;
	 Cliente clienteActual;
	 Categoria categoriaActual;
	 Producto productoActual;
	public void init() {
		String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			ventasDAO = new VentasDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
 
	
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "login":
				login(request, response);
				break;
			case "principal":
				principal(request, response);
				break;
			case "menu_categorias":
				menu_categorias(request, response);
				break;
			case "menu_productos":
				menu_productos(request, response);
				break;
			case "menu_clientes":
				menu_clientes(request, response);
				break;
			case "nuevo_cliente":
				nuevo_cliente(request, response);
				break;
			case "nuevo_categoria":
				nuevo_categoria(request, response);
				break;
			case "nuevo_producto":
				nuevo_producto(request, response);
				break;
			case "nuevo_usuario":
				nuevo_usuario(request, response);
				break;
			case "registrar_cliente":
				System.out.println("entro");
				registrar_cliente(request, response);
				break;
			case "registrar_usuario":
				System.out.println("entro");
				registrar_usuario(request, response);
				break;
			case "registrar_categoria":
				System.out.println("entro");
				registrar_categoria(request, response);
				break;
			case "registrar_producto":
				System.out.println("entro");
				registrar_producto(request, response);
				break;
			case "mostrar_clientes":
				mostrar_clientes(request, response);
				break;
			case "mostrar_categorias":
				mostrar_categorias(request, response);
				break;
			case "mostrar_productos":
				mostrar_productos(request, response);
				break;
			case "showedit_cliente":
				showEditar_cliente(request, response);
				break;	
			case "showedit_categoria":
				showEditar_categoria(request, response);
				break;
			case "showedit_producto":
				showEditar_producto(request, response);
				break;
			case "editar_cliente":
				editar_cliente(request, response);
				break;
			case "editar_categoria":
				editar_categoria(request, response);
				break;
			case "editar_producto":
				editar_producto(request, response);
				break;
			case "eliminar_cliente":
				eliminar_cliente(request, response);
				break;
			case "eliminar_categoria":
				eliminar_categoria(request, response);
				break;
			case "eliminar_producto":
				eliminar_producto(request, response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("Hola Servlet..");
			doGet(request, response);
		}
		private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}	
		private void login (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Usuario usuario = new Usuario(0,request.getParameter("nombre"),request.getParameter("contraseña"));
			if(ventasDAO.Validar_usuario(usuario)) {
				request.getSession().setAttribute("user", usuario.getNombre());
				request.getSession().setAttribute("password", usuario.getContraseña());
				RequestDispatcher dispatcher = request.getRequestDispatcher("principal.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			
			
		}
		private void principal (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("principal.jsp");
			dispatcher.forward(request, response);
		}		
		private void menu_categorias (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/menu_categorias.jsp");
			dispatcher.forward(request, response);
		}
		private void menu_productos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/menu_productos.jsp");
			dispatcher.forward(request, response);
		}
		private void menu_clientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/menu_clientes.jsp");
			dispatcher.forward(request, response);
		}
		private void nuevo_cliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/clientes/registrar_cliente.jsp");
			dispatcher.forward(request, response);
		}
		private void nuevo_categoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/categorias/registrar_categoria.jsp");
			dispatcher.forward(request, response);
		}
		private void nuevo_usuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/usuarios/registrar_usuario.jsp");
			dispatcher.forward(request, response);
		}
		private void nuevo_producto(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("vistas/productos/registrar_producto.jsp");
			List<Categoria> listaCategorias= ventasDAO.listarCategorias();
			request.setAttribute("lista_categorias", listaCategorias);
			dispatcher.forward(request, response);
		}
		private void registrar_cliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			
			if(request.getParameter("cedula").equals("") || request.getParameter("nombre").equals("") || request.getParameter("apellido").equals("")) {	
				System.out.println("Llenar campos obligatorios");
				RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/clientes/registrar_cliente.jsp");
				dispatcher.forward(request, response);
				
			}
			else if(!ventasDAO.ValidacionLetras(request.getParameter("nombre")) || !ventasDAO.ValidacionLetras(request.getParameter("apellido"))){
				System.out.println("Error en el formulario");
				RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/clientes/registrar_cliente.jsp");
				dispatcher.forward(request, response);
			}else {
				Cliente cliente = new Cliente(0,request.getParameter("cedula"), request.getParameter("nombre"), request.getParameter("apellido"),request.getParameter("genero"),request.getParameter("categoria"),request.getParameter("correo"));
				ventasDAO.insertar_cliente(cliente);
				RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/menu_clientes.jsp");
				dispatcher.forward(request, response);
			}
		}
		private void registrar_usuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			if(request.getParameter("username").equals("") || request.getParameter("password").equals("") ||	!ventasDAO.ValidacionAlfanumerico(request.getParameter("username")) || !ventasDAO.ValidacionAlfanumerico(request.getParameter("password")) 
					|| !ventasDAO.ValidacionLimite(request.getParameter("username"), 8, 10) || !ventasDAO.ValidacionLimite(request.getParameter("password"), 8, 10)) {	
				System.out.println("Error en el formulario");
				RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/usuarios/registrar_usuario.jsp");
				dispatcher.forward(request, response);
			}else {	
			Usuario usuario = new Usuario(0,request.getParameter("username"),request.getParameter("password"));
			ventasDAO.insertar_usuario(usuario);			
			index(request, response);		
			}	
		}
		private void registrar_categoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			Categoria categoria = new Categoria(0,request.getParameter("nombre"));
			ventasDAO.insertar_categoria(categoria);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/menu_categorias.jsp");
			dispatcher.forward(request, response);
		}
		private void registrar_producto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			Producto producto = new Producto(0, request.getParameter("nombre"), Integer.parseInt(request.getParameter("categoria")),request.getParameter("codigo"));
			ventasDAO.insertar_producto(producto);		
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/menu_productos.jsp");
			dispatcher.forward(request, response);
		}
		private void mostrar_clientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/clientes/mostrar_cliente.jsp");
			List<Cliente> listaClientes= ventasDAO.listarClientes();
			request.setAttribute("lista_clientes", listaClientes);
			dispatcher.forward(request, response);
		}
		private void mostrar_categorias(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/categorias/mostrar_categoria.jsp");
			List<Categoria> listaCategorias= ventasDAO.listarCategorias();
			request.setAttribute("lista_categorias", listaCategorias);
			dispatcher.forward(request, response);
		}
		private void mostrar_productos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/productos/mostrar_producto.jsp");
			List<Producto> listaProductos= ventasDAO.listarProductos();
			request.setAttribute("lista_productos", listaProductos);
			dispatcher.forward(request, response);
		}
		private void showEditar_cliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			Cliente cliente = ventasDAO.obtenerPorId_cliente(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("cliente", cliente);
			clienteActual = cliente;
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/clientes/editar_cliente.jsp");
			dispatcher.forward(request, response);
		}
		private void showEditar_categoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			Categoria categoria = ventasDAO.obtenerPorId_categoria(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("categoria", categoria);
			categoriaActual = categoria;
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/categorias/editar_categoria.jsp");
			dispatcher.forward(request, response);
		}
		private void showEditar_producto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			Producto producto = ventasDAO.obtenerPorId_producto(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("producto", producto);	
			List<Categoria> listaCategorias= ventasDAO.listarCategorias();
			request.setAttribute("lista_categorias", listaCategorias);
			productoActual = producto;
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/productos/editar_producto.jsp");
			dispatcher.forward(request, response);
		}
		private void editar_cliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			
			if(clienteActual.getNombre().equals(request.getParameter("nombre")) && clienteActual.getApellido().equals(request.getParameter("apellido")) && clienteActual.getCedula().equals(request.getParameter("cedula")) && clienteActual.getGenero().equals(request.getParameter("genero"))
					&& clienteActual.getCategoria().equals(request.getParameter("categoria")) && clienteActual.getCorreo().equals(request.getParameter("correo"))) {
				System.out.println("No se realizaron modificaciones");
				RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/menu_clientes.jsp");
				dispatcher.forward(request, response);

			}else if(!ventasDAO.ValidacionLetras(request.getParameter("nombre")) || !ventasDAO.ValidacionLetras(request.getParameter("apellido"))){
				System.out.println("Error en el formulario");			
				 Cliente cliente = clienteActual;
				request.setAttribute("cliente", cliente);		
				RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/clientes/editar_cliente.jsp");
				dispatcher.forward(request, response);
			}else {		
				System.out.println("in");
				Cliente cliente = new Cliente(Integer.parseInt(request.getParameter("id")), request.getParameter("cedula"), request.getParameter("nombre"), request.getParameter("apellido"),request.getParameter("genero"),request.getParameter("categoria"),request.getParameter("correo"));
				System.out.println("admin cliente" + cliente.toString());
				ventasDAO.actualizar_cliente(cliente);
				mostrar_clientes(request, response);
			}
		}
		private void editar_categoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Categoria categoria =  Categoria.builder().setId(Integer.parseInt(request.getParameter("id"))).setNombre(request.getParameter("nombre")).build();
			ventasDAO.actualizar_categoria(categoria);
			mostrar_categorias(request, response);

		}
		private void editar_producto(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Producto producto = new Producto(Integer.parseInt(request.getParameter("id")),  request.getParameter("nombre"), Integer.parseInt(request.getParameter("categoria")),request.getParameter("codigo"));
			ventasDAO.actualizar_producto(producto);
			mostrar_productos(request, response);

		}
		private void eliminar_cliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Cliente cliente = ventasDAO.obtenerPorId_cliente(Integer.parseInt(request.getParameter("id")));
			System.out.println(cliente.toString());
			ventasDAO.eliminar_cliente(cliente);
			mostrar_clientes(request, response);
			
		}
		private void eliminar_categoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Categoria categoria = ventasDAO.obtenerPorId_categoria(Integer.parseInt(request.getParameter("id")));
			ventasDAO.eliminar_categoria(categoria);			
			mostrar_categorias(request, response);
			
		}
		private void eliminar_producto(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Producto producto = ventasDAO.obtenerPorId_producto(Integer.parseInt(request.getParameter("id")));
			ventasDAO.eliminar_producto(producto);
			mostrar_productos(request, response);

			
		}
	
}
