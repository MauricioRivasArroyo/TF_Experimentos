package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
 
public class Conexion {
    private Connection jdbcConnection;
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    
    
    public Conexion(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
 
	public Connection conectar() {
		Connection con = null;
		
		String password = "";
		String usuario = "root";
		String url = "jdbc:mysql://localhost:3306/ventas?user=" + usuario
				 + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		try {
			con = DriverManager.getConnection(url);
			if (con != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
		return con;        
    }
     
    public void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
 
	public Connection getJdbcConnection() {
		return jdbcConnection;
	}  
 
}
