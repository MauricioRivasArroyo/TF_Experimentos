package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
 
public abstract class Conexion {
    protected Connection jdbcConnection;
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;
    public static final int MySql = 1;
    public static final int SqlServer = 2;
    
    public Conexion(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		Conexion.setJdbcURL(jdbcURL);
		Conexion.setJdbcUsername(jdbcUsername);
		Conexion.setJdbcPassword(jdbcPassword);
	}
    public abstract void conectar() throws SQLException;
	
	
    public void desconectar() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
 
	public Connection getJdbcConnection() {
		return jdbcConnection;
	}  
	
	 public static Conexion getSubFactory(int tipo) {
		 switch(tipo) {
		 case MySql:
			 return new SubFactoryMySql(getJdbcURL(), getJdbcUsername(), getJdbcPassword());
		 case SqlServer:
			 return new SubFactorySqlServer(getJdbcURL(), getJdbcUsername(), getJdbcPassword());
		default:
			break;
		 }
		 return null;
	 }
	public static String getJdbcUsername() {
		return jdbcUsername;
	}
	public static void setJdbcUsername(String jdbcUsername) {
		Conexion.jdbcUsername = jdbcUsername;
	}
	public static String getJdbcURL() {
		return jdbcURL;
	}
	public static void setJdbcURL(String jdbcURL) {
		Conexion.jdbcURL = jdbcURL;
	}
	public static String getJdbcPassword() {
		return jdbcPassword;
	}
	public static void setJdbcPassword(String jdbcPassword) {
		Conexion.jdbcPassword = jdbcPassword;
	}
 
}
