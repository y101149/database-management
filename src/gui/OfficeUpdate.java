package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import entity.BranchOffices;
import entity.Buyers;
import entity.SalesAgent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class OfficeUpdate {

	private JFrame frame;
	private JTextField name;
	private JTextField address;
	private JTextField mail;
	private JComboBox manager = new JComboBox();
	private JComboBox pk = new JComboBox();
	private JTextField office;
	private JTextField phone;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					OfficeUpdate window = new OfficeUpdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public OfficeUpdate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 563);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddAgent = new JLabel("Update Office");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(28, 0, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("Officeid");
		lblAgentid.setBounds(61, 111, 83, 26);
		frame.getContentPane().add(lblAgentid);
		
		JLabel lblFirstname = new JLabel("Officename");
		lblFirstname.setBounds(61, 178, 83, 26);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Address");
		lblLastname.setBounds(61, 248, 83, 26);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblExt = new JLabel("MailBOX");
		lblExt.setBounds(61, 321, 83, 26);
		frame.getContentPane().add(lblExt);
		
		name = new JTextField();
		name.setEditable(false);
		name.setBounds(209, 177, 134, 28);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setEditable(false);
		address.setColumns(10);
		address.setBounds(209, 247, 134, 28);
		frame.getContentPane().add(address);
		
		mail = new JTextField();
		mail.setEditable(false);
		mail.setColumns(10);
		mail.setBounds(209, 320, 134, 28);
		frame.getContentPane().add(mail);
		
		pk = new JComboBox();
		pk.setToolTipText("");
		String sql = "select * from branch_offices";
		JDBCConnection.executeQuery(sql);
		while(JDBCConnection.next())
		{
			try {
				pk.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		pk.setBounds(126, 56, 134, 27);
		frame.getContentPane().add(pk);
		
		
		String sqlstr = "select agentid from sales_agents";
		JDBCConnection.executeQuery(sqlstr);
	    manager = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				manager.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		manager.setToolTipText("");
		manager.setBounds(209, 453, 134, 27);
		frame.getContentPane().add(manager);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				if(office.getText().isEmpty())
					flag = 1;
				if(flag == 0)
				{
					BranchOffices bo = new  BranchOffices();
					bo.setOfficeid(office.getText());
					bo.setOfficename(name.getText());
					bo.setAddress(address.getText());
					bo.setMailbox(mail.getText());
					bo.setPhonenumber(phone.getText());
					bo.setManagerid(manager.getSelectedItem().toString());
					String sql = "update branch_offices set officename="
							+"'"+ bo.getOfficename()+"',"+"address = "+ "'"+bo.getAddress()+"'," + "mailbox ="
							+"'"+ bo.getMailbox()+ "',"+"phonenumber = " + "'"+bo.getPhonenumber()+"',"+"managerid ="
							+"'"+ bo.getManagerid()+"'" +" where officeid = "+bo.getOfficeid() ;
						JDBCConnection.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Update Success", "Success", JOptionPane.ERROR_MESSAGE);				
					
				}
				else
					JOptionPane.showMessageDialog(null, "PLS SEARCH FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);				

					
			}
		});
		submit.setBounds(61, 506, 117, 29);
		frame.getContentPane().add(submit);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancel.setBounds(213, 506, 117, 29);
		frame.getContentPane().add(cancel);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(61, 385, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblAgentid_1 = new JLabel("ManagerID");
		lblAgentid_1.setBounds(61, 452, 98, 26);
		frame.getContentPane().add(lblAgentid_1);
		
		office = new JTextField();
		office.setEditable(false);
		office.setColumns(10);
		office.setBounds(209, 110, 134, 28);
		frame.getContentPane().add(office);
		
		phone = new JTextField();
		phone.setEditable(false);
		phone.setColumns(10);
		phone.setBounds(209, 384, 134, 28);
		frame.getContentPane().add(phone);
		
		JLabel lblInputPk = new JLabel("Input PK");
		lblInputPk.setBounds(61, 56, 83, 26);
		frame.getContentPane().add(lblInputPk);
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String primary = "";
				primary = pk.getSelectedItem().toString();
				BranchOffices bo = new BranchOffices();
				String sql1 = "select * from branch_offices where officeid ="+primary; 
				JDBCConnection.executeQuery(sql1);
				while(JDBCConnection.next())
				{
					try {
						bo.setOfficeid(JDBCConnection.resultSet.getString(1).toString());
						bo.setOfficename(JDBCConnection.resultSet.getString(2).toString());
						if(JDBCConnection.resultSet.getObject(3) == null)
							bo.setAddress("");
						else
							bo.setAddress(JDBCConnection.resultSet.getString(3).toString());
						
						if(JDBCConnection.resultSet.getObject(4) == null)
							bo.setMailbox("");
						else
							bo.setMailbox(JDBCConnection.resultSet.getString(4).toString());
						
						if(JDBCConnection.resultSet.getObject(5) == null)
							bo.setPhonenumber("");
						else
							bo.setPhonenumber(JDBCConnection.resultSet.getString(5).toString());
						
						if(JDBCConnection.resultSet.getObject(6) == null)
							bo.setManagerid("");
						else
							bo.setManagerid(JDBCConnection.resultSet.getString(6).toString());
							
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				office.setText(bo.getOfficeid());
				name.setText(bo.getOfficename());
				address.setText(bo.getAddress());
				mail.setText(bo.getMailbox());
				phone.setText(bo.getPhonenumber());
				manager.setSelectedItem(bo.getManagerid());
				
				name.setEditable(true);
				address.setEditable(true);
				mail.setEditable(true);
				phone.setEditable(true);
				manager.setEditable(true);

			}
		});
		btnSearch.setBounds(291, 56, 117, 29);
		frame.getContentPane().add(btnSearch);
		
		
	}
}
