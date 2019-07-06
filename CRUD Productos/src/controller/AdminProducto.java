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
 
import dao.ProductoDAO;
import model.Categoria;
import model.Producto;
import model.ProductoResponse;

@WebServlet("/adminProducto")
public class AdminProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductoDAO productoDAO;
 
	public void init() {
		String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
 
			productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
 
	
	public AdminProducto() {
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
		Producto producto = new Producto(0, request.getParameter("nombre"), Integer.parseInt(request.getParameter("categoria")),request.getParameter("codigo"));
		productoDAO.insertar(producto);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/registrar.jsp");
		List<Categoria> listaCategorias= productoDAO.listarCategorias();
		request.setAttribute("lista", listaCategorias);
		dispatcher.forward(request, response);
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/mostrar.jsp");
		List<Producto> listaProductos= productoDAO.listarProductos();
		request.setAttribute("lista", listaProductos);
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Producto producto = productoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("producto", producto);	
		List<Categoria> listaCategorias= productoDAO.listarCategorias();
		request.setAttribute("lista", listaCategorias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Producto producto = new Producto(Integer.parseInt(request.getParameter("id")),  request.getParameter("nombre"), Integer.parseInt(request.getParameter("categoria")),request.getParameter("codigo"));
		productoDAO.actualizar(producto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Producto producto = productoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		productoDAO.eliminar(producto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
}