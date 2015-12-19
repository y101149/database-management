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

import oracle.sql.converter.JdbcCharacterConverters;
import jdbc.JDBCConnection;

public class ContactUpdate {

	private JFrame frame;
	private JTextField first;
	private JTextField last;
	private JTextField tittle;
	
	private JTextField contact;
	private JTextField phone;
	private JComboBox pk = new JComboBox();
	


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					ContactUpdate window = new ContactUpdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public ContactUpdate() {
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
		
		JLabel lblAddAgent = new JLabel("Update Contact Person");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(6, 0, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("ContactID");
		lblAgentid.setBounds(61, 113, 83, 26);
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
		first.setEditable(false);
		first.setBounds(209, 248, 134, 28);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setEditable(false);
		last.setColumns(10);
		last.setBounds(209, 317, 134, 28);
		frame.getContentPane().add(last);
		
		tittle = new JTextField();
		tittle.setEditable(false);
		tittle.setColumns(10);
		tittle.setBounds(212, 170, 134, 28);
		frame.getContentPane().add(tittle);
		
		pk = new JComboBox();
		pk.setBounds(142, 52, 117, 27);
		String sql = "select * from contact_person";
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
		frame.getContentPane().add(pk);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				if(contact.getText().isEmpty())
				{
					flag = 1;
				}
				if(flag == 0)
				{
					ContactPerson cp = new ContactPerson();
					cp.setId(contact.getText());
					cp.setTittle(tittle.getText());
					cp.setFirstname(first.getText());
					cp.setLastname(last.getText());
					cp.setPhonenumber(phone.getText());
				
					String sql = "update contact_person set tittle="
						+"'"+ cp.getTittle()+"',"+"firstname = "+ "'"+cp.getFirstname()+"'," + "lastname ="
						+"'"+ cp.getLastname()+ "',"+"phonenumber = " + "'"+cp.getPhonenumber()
						+"'" +" where id = "+cp.getId() ;
					JDBCConnection.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Update Success", "Success", JOptionPane.ERROR_MESSAGE);				
				}
				else
					JOptionPane.showMessageDialog(null, "PLS SEARCH FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);				

					
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
		contact.setEditable(false);
		contact.setColumns(10);
		contact.setBounds(212, 112, 134, 28);
		frame.getContentPane().add(contact);
		
		phone = new JTextField();
		phone.setEditable(false);
		phone.setColumns(10);
		phone.setBounds(209, 390, 134, 28);
		frame.getContentPane().add(phone);
		
		JLabel lblInputPk = new JLabel("Input PK");
		lblInputPk.setBounds(61, 56, 61, 16);
		frame.getContentPane().add(lblInputPk);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String primary = "";
				ContactPerson cp = new ContactPerson();
				
				primary = pk.getSelectedItem().toString();
				String sql1 = "select * from contact_person where id ="+primary; 
				JDBCConnection.executeQuery(sql1);
				while(JDBCConnection.next())
				{
					try {
						cp.setId(JDBCConnection.resultSet.getString(1).toString());
						cp.setTittle(JDBCConnection.resultSet.getString(2).toString());
						cp.setFirstname(JDBCConnection.resultSet.getString(3).toString());
						cp.setLastname(JDBCConnection.resultSet.getString(4).toString());
						if(JDBCConnection.resultSet.getObject(5) == null)
							cp.setPhonenumber("");
						else
							cp.setPhonenumber(JDBCConnection.resultSet.getString(5).toString());
											
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
					contact.setText(cp.getId());
					tittle.setText(cp.getTittle());
					first.setText(cp.getFirstname());
					last.setText(cp.getLastname());
					phone.setText(cp.getPhonenumber());
					
					tittle.setEditable(true);
					first.setEditable(true);
					last.setEditable(true);
					phone.setEditable(true);
					
				
				
			}
			
			
		});
		btnNewButton.setBounds(285, 51, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
	    
		
		
	}
}
