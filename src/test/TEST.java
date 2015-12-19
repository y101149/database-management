package test;
import java.sql.SQLException;

import entity.Sellers;
import jdbc.JDBCConnection;


public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		JDBCConnection conn = new JDBCConnection();
//		conn.setConnection();
//		String ee = "select officename,address from Branch_Offices";
//		conn.executeQuery(ee);
		Sellers se = new Sellers();
		se.setSellerid("");
		
		
		String a = "";
		System.out.println(a.toString());
		//System.out.println(conn.next());
//		while(conn.next())
//		{
//			try {
//				System.out.println(conn.resultSet.getString(2));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
	}

}
