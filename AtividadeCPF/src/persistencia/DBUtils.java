package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private String url = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=corona1;namedPipes=true";
	private String user = "koiti";
	private String password = "fatec";
	private Connection con;
	private static DBUtils dbutils;

	private DBUtils() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBUtils getInstance() {
		if (dbutils == null) {
			dbutils = new DBUtils();
		}
		return dbutils;
	}

	public Connection getConnection() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
		return con;
	}
}