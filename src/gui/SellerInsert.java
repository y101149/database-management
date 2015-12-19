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
import entity.SalesAgent;
import entity.Sellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class SellerInsert {

	private JFrame frame;
	private JTextField first;
	private JTextField last;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JComboBox agent = new JComboBox();
	private JComboBox contact = new JComboBox();
	private JTextField sellerid;
	private JTextField zip;
	private JTextField phone;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					SellerInsert window = new SellerInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public SellerInsert() {
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
		
		JLabel lblAddAgent = new JLabel("Add Seller");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 20, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("SellerID");
		lblAgentid.setBounds(61, 76, 83, 26);
		frame.getContentPane().add(lblAgentid);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(61, 121, 83, 26);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(61, 169, 83, 26);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblExt = new JLabel("Street");
		lblExt.setBounds(61, 218, 83, 26);
		frame.getContentPane().add(lblExt);
		
		JLabel lblHomenumber = new JLabel("City");
		lblHomenumber.setBounds(61, 269, 98, 26);
		frame.getContentPane().add(lblHomenumber);
		
		JLabel lblOfficeid = new JLabel("State");
		lblOfficeid.setBounds(61, 314, 83, 26);
		frame.getContentPane().add(lblOfficeid);
		
		first = new JTextField();
		first.setBounds(209, 120, 134, 28);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(209, 168, 134, 28);
		frame.getContentPane().add(last);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(209, 217, 134, 28);
		frame.getContentPane().add(street);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(209, 268, 134, 28);
		frame.getContentPane().add(city);
		
		state = new JTextField();
		state.setColumns(10);
		state.setBounds(209, 313, 134, 28);
		frame.getContentPane().add(state);
		
		String sqlstr = "select agentid from sales_agents";
		JDBCConnection.executeQuery(sqlstr);
	    agent = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				agent.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		agent.setToolTipText("");
		agent.setBounds(209, 467, 134, 27);
		frame.getContentPane().add(agent);
		
		String sqlstr2 = "select id from contact_person";
		JDBCConnection.executeQuery(sqlstr2);
		contact = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				contact.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		contact.setToolTipText("");
		contact.setBounds(209, 517, 134, 27);
		frame.getContentPane().add(contact);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int flag1 = 0;
				Sellers se = new Sellers();
				if(sellerid.getText().isEmpty())
					flag = 1;
				else
				{
					String sql = "select sellerid from sellers";
					JDBCConnection.executeQuery(sql);
					while(JDBCConnection.next())
					{
						try {
							if(JDBCConnection.resultSet.getString(1).toString().equals(sellerid.getText().toString()))
							{
								flag1 = 1;
								break;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					se.setSellerid(sellerid.getText());

				}
				
				if(first.getText().isEmpty())
					flag = 1;
				else
					se.setFirstname(first.getText());
				
				if(last.getText().isEmpty())
					flag = 1;
				else
					se.setLastname(last.getText());
				
				
				if(street.getText().isEmpty())
					se.setStreet("null");
				else
					se.setStreet(street.getText());
				
				if(city.getText().isEmpty())
					se.setCity("null");
				else
					se.setCity(city.getText());
				
				if(state.getText().isEmpty())
					se.setState("null");
				else
					se.setState(state.getText());
				
				if(zip.getText().isEmpty())
					se.setZip("null");
				else
					se.setZip(zip.getText());
				if(phone.getText().isEmpty())
					se.setPhonenumber("null");
				else
					se.setPhonenumber(phone.getText());
				se.setAgentid(agent.getSelectedItem().toString());
				se.setContactpersonid(contact.getSelectedItem().toString());
				
				if(flag == 1)
					JOptionPane.showMessageDialog(null, "Some field must be NOT NULL", "ERROR", JOptionPane.ERROR_MESSAGE);	
				if(flag1 == 1)
					JOptionPane.showMessageDialog(null, "Invalid Primary KEY", "Fail", JOptionPane.ERROR_MESSAGE);				
				if(flag == 0 && flag1 == 0)
				{
					String insertsql = "insert into sellers values("
							+ se.getSellerid()+","+"'"+se.getFirstname()+"'"+","
							+"'"+se.getLastname()+"'"+"," +"'"+ se.getStreet()
							+"'"+","+"'"+se.getCity()+"'"+","+"'"+se.getState()
							+"'"+","+"'"+se.getZip()+"'"+ ","+"'"+se.getPhonenumber()
							+"'"+","+se.getAgentid()+","+se.getContactpersonid()
							+",null,"+"null,"+"null,"+"null"+")";
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
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(61, 371, 83, 26);
		frame.getContentPane().add(lblZip);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(61, 423, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblAgentid_1 = new JLabel("AgentID");
		lblAgentid_1.setBounds(61, 471, 98, 26);
		frame.getContentPane().add(lblAgentid_1);
		
		sellerid = new JTextField();
		sellerid.setColumns(10);
		sellerid.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(sellerid);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(209, 370, 134, 28);
		frame.getContentPane().add(zip);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(209, 422, 134, 28);
		frame.getContentPane().add(phone);
		
		JLabel lblContactid = new JLabel("ContactID");
		lblContactid.setBounds(61, 516, 98, 26);
		frame.getContentPane().add(lblContactid);
		
	    
		
		
	}
}
