package gui;

import jdbc.JDBCConnection;

public class Init {
	public static void main(String[] args) {
		JDBCConnection.setConnection();
		LoginGUI login = new LoginGUI();
		login.run();
	}

}
