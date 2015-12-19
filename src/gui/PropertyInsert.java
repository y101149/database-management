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
import entity.Properties;
import entity.SalesAgent;
import entity.Sellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class PropertyInsert {

	private JFrame frame;
	private JTextField type;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JComboBox seller = new JComboBox();
	private JTextField property;
	private JTextField zip;
	private JTextField ask;
	private JTextField listdate;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					PropertyInsert window = new PropertyInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public PropertyInsert() {
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
		
		JLabel lblAddAgent = new JLabel("Add Property");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 20, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("PropertyID");
		lblAgentid.setBounds(61, 76, 83, 26);
		frame.getContentPane().add(lblAgentid);
		
		JLabel lblFirstname = new JLabel("Type");
		lblFirstname.setBounds(61, 121, 83, 26);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblExt = new JLabel("Street");
		lblExt.setBounds(61, 169, 83, 26);
		frame.getContentPane().add(lblExt);
		
		JLabel lblHomenumber = new JLabel("City");
		lblHomenumber.setBounds(61, 218, 98, 26);
		frame.getContentPane().add(lblHomenumber);
		
		JLabel lblOfficeid = new JLabel("State");
		lblOfficeid.setBounds(61, 269, 83, 26);
		frame.getContentPane().add(lblOfficeid);
		
		type = new JTextField();
		type.setBounds(209, 120, 134, 28);
		frame.getContentPane().add(type);
		type.setColumns(10);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(209, 168, 134, 28);
		frame.getContentPane().add(street);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(209, 217, 134, 28);
		frame.getContentPane().add(city);
		
		state = new JTextField();
		state.setColumns(10);
		state.setBounds(209, 268, 134, 28);
		frame.getContentPane().add(state);
		
		
		
		String sqlstr2 = "select sellerid from sellers";
		JDBCConnection.executeQuery(sqlstr2);
		seller = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				seller.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		seller.setToolTipText("");
		seller.setBounds(209, 500, 134, 27);
		frame.getContentPane().add(seller);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int flag1 = 0;
				Properties pr = new Properties();
				if(property.getText().isEmpty())
					flag = 1;
				else
				{
					String sql = "select propertyid from properties";
					JDBCConnection.executeQuery(sql);
					while(JDBCConnection.next())
					{
						try {
							if(JDBCConnection.resultSet.getString(1).toString().equals(property.getText().toString()))
							{
								flag1 = 1;
								break;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					pr.setPropertyid(property.getText());

				}
				
				if(type.getText().isEmpty())
					pr.setType("null");
				else
					pr.setType(type.getText());
				
				if(street.getText().isEmpty())
					pr.setStreet("null");
				else
					pr.setStreet(street.getText());
				
				if(city.getText().isEmpty())
					pr.setCity("null");
				else
					pr.setCity(city.getText());
				
				if(state.getText().isEmpty())
					pr.setState("null");
				else
					pr.setState(state.getText());
				
				if(zip.getText().isEmpty())
					pr.setZip("null");
				else
					pr.setZip(zip.getText());
				
				if(ask.getText().isEmpty())
					pr.setAskingprice("null");
				else
					pr.setAskingprice(ask.getText());
				
				if(listdate.getText().isEmpty())
					pr.setListdate("null");
				else
					pr.setListdate(listdate.getText());
				
				pr.setSellerid(seller.getSelectedItem().toString());
				
				
				if(flag == 1)
					JOptionPane.showMessageDialog(null, "Some field must be NOT NULL", "ERROR", JOptionPane.ERROR_MESSAGE);	
				if(flag1 == 1)
					JOptionPane.showMessageDialog(null, "Invalid Primary KEY", "Fail", JOptionPane.ERROR_MESSAGE);				
				if(flag == 0 && flag1 == 0)
				{
					String insertsql = "insert into properties values("
							+ pr.getPropertyid()+","+"'"+pr.getType()+"'"+","
							+"'"+pr.getStreet()+"'"+"," + "'"+pr.getCity()
							+"'"+","+"'"+pr.getState()+"'"+","+"'"+pr.getZip()
							+"'"+","+"'"+pr.getAskingprice()+"'"+ ","+"'"+pr.getListdate()
							+","+pr.getSellerid()
							+"'"+",null,"+"null,"+"null,"+"null"+")";
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
		lblZip.setBounds(61, 326, 83, 26);
		frame.getContentPane().add(lblZip);
		
		JLabel lblPhonenumber = new JLabel("AskingPrice");
		lblPhonenumber.setBounds(61, 382, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		property = new JTextField();
		property.setColumns(10);
		property.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(property);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(209, 325, 134, 28);
		frame.getContentPane().add(zip);
		
		ask = new JTextField();
		ask.setColumns(10);
		ask.setBounds(209, 381, 134, 28);
		frame.getContentPane().add(ask);
		
		JLabel lblContactid = new JLabel("SellerID");
		lblContactid.setBounds(61, 499, 98, 26);
		frame.getContentPane().add(lblContactid);
		
		JLabel lblListdate = new JLabel("ListDate");
		lblListdate.setBounds(61, 439, 98, 26);
		frame.getContentPane().add(lblListdate);
		
		listdate = new JTextField();
		listdate.setColumns(10);
		listdate.setBounds(209, 438, 134, 28);
		frame.getContentPane().add(listdate);
		
	    
		
		
	}
}
