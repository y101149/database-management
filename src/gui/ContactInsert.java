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

import entity.Buyers;
import entity.ContactPerson;
import entity.SalesAgent;
import entity.Sellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class ContactInsert {

	private JFrame frame;
	private JTextField first;
	private JTextField last;
	private JTextField tittle;
	
	private JTextField contact;
	private JTextField phone;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					ContactInsert window = new ContactInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public ContactInsert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 626);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddAgent = new JLabel("Add Contact Person");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(6, 0, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("ContactID");
		lblAgentid.setBounds(61, 98, 83, 26);
		frame.getContentPane().add(lblAgentid);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(61, 249, 83, 26);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(61, 318, 83, 26);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblExt = new JLabel("Tittle");
		lblExt.setBounds(61, 171, 83, 26);
		frame.getContentPane().add(lblExt);
		
		first = new JTextField();
		first.setBounds(209, 248, 134, 28);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(209, 317, 134, 28);
		frame.getContentPane().add(last);
		
		tittle = new JTextField();
		tittle.setColumns(10);
		tittle.setBounds(212, 170, 134, 28);
		frame.getContentPane().add(tittle);
		
		
		
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int flag1 = 0;
				ContactPerson cp = new ContactPerson();
				if(contact.getText().isEmpty())
					flag = 1;
				else
				{

					String sql = "select id from contact_person";
					JDBCConnection.executeQuery(sql);
					while(JDBCConnection.next())
					{
						try {
							if(JDBCConnection.resultSet.getString(1).toString().equals(contact.getText().toString()))
							{
								flag1 = 1;
								break;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					cp.setId(contact.getText());
				
				}
				
				if(tittle.getText().isEmpty())
					flag = 1;
				else
					cp.setTittle(tittle.getText());
				
				if(first.getText().isEmpty())
					flag = 1;
				else
					cp.setFirstname(first.getText());
				
				if(last.getText().isEmpty())
					flag = 1;
				else
					cp.setLastname(last.getText());
				
				if(phone.getText().isEmpty())
					cp.setPhonenumber("null");
				else
					cp.setPhonenumber(phone.getText());
				
				
				
				if(flag == 1)
					JOptionPane.showMessageDialog(null, "Some field must be NOT NULL", "ERROR", JOptionPane.ERROR_MESSAGE);	
				if(flag1 == 1)
					JOptionPane.showMessageDialog(null, "Invalid Primary KEY", "Fail", JOptionPane.ERROR_MESSAGE);				
				if(flag == 0 && flag1 == 0)
				{
					String insertsql = "insert into contact_person values("
							+ cp.getId()+","+"'"+cp.getTittle()+"'"+","
							+"'"+cp.getFirstname()+"'"+"," + "'"+cp.getLastname()
							+"'"+","+"'"+cp.getPhonenumber()+"'"+")";
					JOptionPane.showMessageDialog(null, "Insert Success", "Success", JOptionPane.ERROR_MESSAGE);				

					//System.out.println(insertsql);
					JDBCConnection.executeUpdate(insertsql);
				
					
				}
			}
		});
		submit.setBounds(67, 554, 117, 29);
		frame.getContentPane().add(submit);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancel.setBounds(226, 554, 117, 29);
		frame.getContentPane().add(cancel);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(61, 391, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		contact = new JTextField();
		contact.setColumns(10);
		contact.setBounds(212, 97, 134, 28);
		frame.getContentPane().add(contact);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(209, 390, 134, 28);
		frame.getContentPane().add(phone);
		
	    
		
		
	}
}
