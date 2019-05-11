package model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SubFactorySqlServer extends Conexion {

	
	public SubFactorySqlServer(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super(jdbcURL, jdbcUsername, jdbcPassword);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void conectar() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(getJdbcURL());
        }		
	}

}
