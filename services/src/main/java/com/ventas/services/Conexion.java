package com.ventas.services;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
 
public class Conexion {
    private Connection jdbcConnection;   
    
    
    public Conexion() {
		
	}
 
	public void conectar() throws SQLException, URISyntaxException, ClassNotFoundException {
		URI dbUri = new URI("mysql://ba120fb89b93ab:679237cc@us-cdbr-iron-east-02.cleardb.net/heroku_c4a58849e3dc425?reconnect=true");

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
	    if(jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); 
            } catch (ClassNotFoundException e) {
              throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(dbUrl,username,password);
        }
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
