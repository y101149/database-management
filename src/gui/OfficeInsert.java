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

public class OfficeInsert {

	private JFrame frame;
	private JTextField name;
	private JTextField address;
	private JTextField mail;
	private JComboBox manager = new JComboBox();
	private JTextField office;
	private JTextField phone;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					OfficeInsert window = new OfficeInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public OfficeInsert() {
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
		
		JLabel lblAddAgent = new JLabel("Add Office");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 20, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("Officeid");
		lblAgentid.setBounds(61, 76, 83, 26);
		frame.getContentPane().add(lblAgentid);
		
		JLabel lblFirstname = new JLabel("Officename");
		lblFirstname.setBounds(61, 153, 83, 26);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Address");
		lblLastname.setBounds(61, 229, 83, 26);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblExt = new JLabel("MailBOX");
		lblExt.setBounds(61, 300, 83, 26);
		frame.getContentPane().add(lblExt);
		
		name = new JTextField();
		name.setBounds(209, 152, 134, 28);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(209, 228, 134, 28);
		frame.getContentPane().add(address);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(209, 299, 134, 28);
		frame.getContentPane().add(mail);
		
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
				int flag1 = 0;
				BranchOffices br = new BranchOffices();
				if(office.getText().isEmpty())
					flag = 1;
				else
				{
					String sql = "select officeid from branch_offices";
					JDBCConnection.executeQuery(sql);
					while(JDBCConnection.next())
					{
						try {
							if(JDBCConnection.resultSet.getString(1).toString().equals(office.getText().toString()))
							{
								flag1 = 1;
								break;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					br.setOfficeid(office.getText());
				
				}
				
				if(name.getText().isEmpty())
					flag = 1;
				else
					br.setOfficename(name.getText());
				
				if(address.getText().isEmpty())
					br.setAddress("null");
				else
					br.setAddress(address.getText());
				
				if(mail.getText().isEmpty())
					br.setMailbox("null");
				else
					br.setMailbox(mail.getText());
				
				if(phone.getText().isEmpty())
					br.setPhonenumber("null");
				else
					br.setPhonenumber(phone.getText());
				
				br.setManagerid(manager.getSelectedItem().toString());
				if(flag == 1)
					JOptionPane.showMessageDialog(null, "Some field must be NOT NULL", "ERROR", JOptionPane.ERROR_MESSAGE);	
				if(flag1 == 1)
					JOptionPane.showMessageDialog(null, "Invalid Primary KEY", "Fail", JOptionPane.ERROR_MESSAGE);				

				if(flag == 0 && flag1 == 0)
				{
					String insertsql ="insert into branch_offices values("
							+br.getOfficeid()+","+"'"+br.getOfficename()+"'"+","
							+"'"+br.getAddress() +"'"+","+"'"+br.getMailbox() +"'"+ ","
							+"'"+br.getPhonenumber()+"'"+","+br.getManagerid()+","
							+"null,"+"null,"+"null,"+"null"+")";
					JOptionPane.showMessageDialog(null, "Insert Success", "Success", JOptionPane.ERROR_MESSAGE);				

					//System.out.println(insertsql);
					JDBCConnection.executeUpdate(insertsql);
				
					
				}
					
				
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
		office.setColumns(10);
		office.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(office);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(209, 384, 134, 28);
		frame.getContentPane().add(phone);
		
		
	}
}
