package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import jdbc.JDBCConnection;
import java.awt.Toolkit;

public class LoginGUI {

	private JFrame frmBarkley;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	
	public void run() {
		try {
				LoginGUI window = new LoginGUI();
				window.frmBarkley.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBarkley = new JFrame();
		frmBarkley.setResizable(false);
		frmBarkley.setTitle("Barkley Brothers Real Estate");
		frmBarkley.setBounds(100, 100, 593, 499);
		frmBarkley.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmBarkley.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To BBIS");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 48));
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(98, 30, 378, 37);
		panel.add(lblNewLabel);
		
		JLabel lblGroup = new JLabel("Group 8");
		lblGroup.setForeground(new Color(255, 0, 0));
		lblGroup.setFont(new Font("Arial", Font.PLAIN, 25));
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setBounds(166, 109, 244, 37);
		panel.add(lblGroup);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUsername.setBounds(114, 206, 121, 22);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblPassword.setBounds(114, 279, 129, 22);
		panel.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(265, 199, 173, 37);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setEchoChar('*');
		password.setBounds(265, 272, 173, 37);
		panel.add(password);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usr = username.getText().trim().toString();
				String pass = password.getText().toString();
//				System.out.println(usr+" "+pass);
				int flag = 0;
				
				String sqlstr = "select username,pass from users";
				JDBCConnection.executeQuery(sqlstr);
//				System.out.println(JDBCConnection.next());
				while (JDBCConnection.next())
				{
					try {
						System.out.println(JDBCConnection.resultSet.getString(1)+" "+JDBCConnection.resultSet.getString(2));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {
						if(usr.equals(JDBCConnection.resultSet.getString(1)) && pass.equals(JDBCConnection.resultSet.getString(2)))
						{
							flag = 1;
							break;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(flag == 1)
				{
					MainFrame mf = new MainFrame();
					mf.run();
					frmBarkley.dispose();
					//System.out.println("loginsuccess");
				}
				else 
				{
					//System.out.println("fail");
					JOptionPane.showMessageDialog(null, "Invalid username and password", "Login Fail", JOptionPane.ERROR_MESSAGE);				
				}
				
			}
		});
		login.setFont(new Font("Arial", Font.PLAIN, 15));
		login.setBounds(130, 374, 117, 29);
		panel.add(login);
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		reset.setFont(new Font("Arial", Font.PLAIN, 15));
		reset.setBounds(338, 374, 117, 29);
		panel.add(reset);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("lib/image.jpg"));
		lblNewLabel_1.setBounds(0, 0, 593, 477);
		panel.add(lblNewLabel_1);
	}
}
