package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class JDBCConnection {
	public static Connection connection;
	public static PreparedStatement pStatement;
	public static Statement statement;
	public static ResultSet resultSet;
	
//	public static final String userid="YIN_YIXIN_IND_PROJ";
//	public static final String password="Yyx.19910719";
	public static final String userid="GROUP_TEAM08_DB_PROJ";
	public static final String password="group8";
	public static final String URL="jdbc:oracle:thin:@oralinux.seas.gwu.edu:1521/team2";
	
	public static void setConnection(){
		connection=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection=DriverManager.getConnection(URL,userid,password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void executeQuery(String sqlString)
	{
		resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlString);
			//System.out.println("SQL STATEMENT RUN");
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SQL STATEMENT ERROR");
			//System.exit(0);
			return;
		}
	}
	
	public static void executePreparedQuery()
	{
		resultSet = null;
		try {
			resultSet = pStatement.executeQuery();
			//System.out.println("SQL PRESTATEMENT RUN");

		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SQL PRESTATEMENT ERROR");
			//System.exit(0);
			return;
		}
	}
	public static void executeUpdate(String sqlString)
	{
		resultSet = null;
		try {
			statement.executeUpdate(sqlString);	
			//System.out.println("SQL UPDATE RUN");

		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SQL UPDATE ERROR");
			//System.exit(0);
			return;
		}
	}
	public static void executePreparedUpdate()
	{
		resultSet = null;
		
		try {
			pStatement.executeUpdate();	
			//System.out.println("SQL UPDATE RUN");

		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SQL UPDATE ERROR");
			System.exit(0);
			return;
		}
	}
	
	public static boolean next()
	{
		try {
			//System.out.println("NEXT");
			return resultSet.next();

		} catch (SQLException e1) {
			System.out.println("ERROR");
			System.exit(0);
		}
		return false;
	}
	
	

}
