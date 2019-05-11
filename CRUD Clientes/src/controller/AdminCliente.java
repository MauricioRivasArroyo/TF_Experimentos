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


import dao.ClienteDAO;
import model.Cliente;

@WebServlet("/adminCliente")
public class AdminCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO clienteDAO;
	Cliente clienteActual;
 
	public void init() {
		String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
 
	
	public AdminCliente() {
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
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditar(request, response);
				break;	
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
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
 
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		if(request.getParameter("cedula").equals("")) {	
			System.out.println("Llenar campos obligatorios");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/registrar.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
			Cliente cliente = new Cliente(0,request.getParameter("cedula"), request.getParameter("nombre"), request.getParameter("apellido"),request.getParameter("genero"),request.getParameter("categoria"),request.getParameter("correo"));
			clienteDAO.insertar(cliente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/registrar.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/mostrar.jsp");
		List<Cliente> listaClientes= clienteDAO.listarClientes();
		request.setAttribute("lista", listaClientes);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Cliente cliente = clienteDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("cliente", cliente);		
		clienteActual = cliente;
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		if(clienteActual.getNombre().equals(request.getParameter("nombre")) && clienteActual.getApellido().equals(request.getParameter("apellido")) && clienteActual.getCedula().equals(request.getParameter("cedula")) && clienteActual.getGenero().equals(request.getParameter("genero"))
				&& clienteActual.getCategoria().equals(request.getParameter("categoria")) && clienteActual.getCorreo().equals(request.getParameter("correo"))) {
			System.out.println("No se realizaron modificaciones");
			Cliente cliente = new Cliente(Integer.parseInt(request.getParameter("id")), request.getParameter("cedula"), request.getParameter("nombre"), request.getParameter("apellido"),request.getParameter("genero"),request.getParameter("categoria"),request.getParameter("correo"));
			clienteDAO.actualizar(cliente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		}else if(!clienteDAO.ValidacionLetras(request.getParameter("nombre"))){
			System.out.println("Error en el formulario");			
			 Cliente cliente = clienteActual;
			request.setAttribute("cliente", cliente);		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editar.jsp");
			dispatcher.forward(request, response);
		}else {		
			Cliente cliente = new Cliente(Integer.parseInt(request.getParameter("id")), request.getParameter("cedula"), request.getParameter("nombre"), request.getParameter("apellido"),request.getParameter("genero"),request.getParameter("categoria"),request.getParameter("correo"));
			clienteDAO.actualizar(cliente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		

	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Cliente cliente = clienteDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		clienteDAO.eliminar(cliente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}