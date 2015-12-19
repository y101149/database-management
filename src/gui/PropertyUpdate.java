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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class PropertyUpdate {

	private JFrame frame;
	private JTextField type;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JComboBox seller = new JComboBox();
	JComboBox pk = new JComboBox();
	private JTextField property;
	private JTextField zip;
	private JTextField ask;
	private JTextField listdate;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					PropertyUpdate window = new PropertyUpdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public PropertyUpdate() {
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
		
		JLabel lblAddAgent = new JLabel("Update Property");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 0, 340, 44);
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
		type.setEditable(false);
		type.setBounds(209, 120, 134, 28);
		frame.getContentPane().add(type);
		type.setColumns(10);
		
		street = new JTextField();
		street.setEditable(false);
		street.setColumns(10);
		street.setBounds(209, 168, 134, 28);
		frame.getContentPane().add(street);
		
		city = new JTextField();
		city.setEditable(false);
		city.setColumns(10);
		city.setBounds(209, 217, 134, 28);
		frame.getContentPane().add(city);
		
		state = new JTextField();
		state.setEditable(false);
		state.setColumns(10);
		state.setBounds(209, 268, 134, 28);
		frame.getContentPane().add(state);
		
	    pk = new JComboBox();
		pk.setToolTipText("");
		pk.setBounds(145, 39, 134, 27);
		String sql = "select * from properties";
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
				if(property.getText().isEmpty())
					flag = 1;
				if(flag == 0)
				{
					Properties pro = new Properties();
					pro.setPropertyid(property.getText());
					pro.setType(type.getText());
					pro.setStreet(street.getText());
					pro.setCity(city.getText());
					pro.setState(state.getText());
					pro.setZip(zip.getText());
					pro.setAskingprice(ask.getText());
					pro.setListdate(listdate.getText());
					pro.setSellerid(seller.getSelectedItem().toString());;
										
					String sql = "update properties set type="
							+"'"+ pro.getType()+"',"+"street = "+ "'"+pro.getStreet()+"'," + "city ="
							+"'"+ pro.getCity()+ "',"+"state = " + "'"+pro.getState()+"'," + "zip ="
							+"'"+ pro.getZip() + "',"+ "askingprice =" + "'"+pro.getAskingprice()+"'," +"listdate =" 
				+"to_date("+"'"+  pro.getListdate()+"',"+"'yyyy-mm-dd hh24:mi:ss'),"+"sellers_sellerid ="+"'"+pro.getSellerid()+"'"+" where propertyid = "+pro.getPropertyid() ;
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
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(61, 326, 83, 26);
		frame.getContentPane().add(lblZip);
		
		JLabel lblPhonenumber = new JLabel("AskingPrice");
		lblPhonenumber.setBounds(61, 382, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		property = new JTextField();
		property.setEditable(false);
		property.setColumns(10);
		property.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(property);
		
		zip = new JTextField();
		zip.setEditable(false);
		zip.setColumns(10);
		zip.setBounds(209, 325, 134, 28);
		frame.getContentPane().add(zip);
		
		ask = new JTextField();
		ask.setEditable(false);
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
		listdate.setEditable(false);
		listdate.setColumns(10);
		listdate.setBounds(209, 438, 134, 28);
		frame.getContentPane().add(listdate);
		
		JLabel lblInputPk = new JLabel("Input PK");
		lblInputPk.setBounds(61, 38, 83, 26);
		frame.getContentPane().add(lblInputPk);
		
		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String primary = "";
				primary = pk.getSelectedItem().toString();
				Properties pro = new Properties();
				
				String sql1 = "select * from properties where propertyid ="+primary; 
				JDBCConnection.executeQuery(sql1);
				while(JDBCConnection.next())
				{
					try {
						pro.setPropertyid(JDBCConnection.resultSet.getString(1).toString());
						
						if(JDBCConnection.resultSet.getObject(2) == null)
							pro.setType("");
						else
							pro.setType(JDBCConnection.resultSet.getString(2).toString());
						
						if(JDBCConnection.resultSet.getObject(3) == null)
							pro.setStreet("");
						else
							pro.setStreet(JDBCConnection.resultSet.getString(3).toString());
						
						if(JDBCConnection.resultSet.getObject(4) == null)
							pro.setCity("");
						else
							pro.setCity(JDBCConnection.resultSet.getString(4).toString());
						
						if(JDBCConnection.resultSet.getObject(5) == null)
							pro.setState("");
						else
							pro.setState(JDBCConnection.resultSet.getString(5).toString());
						
						if(JDBCConnection.resultSet.getObject(6) == null)
							pro.setZip("");
						else
							pro.setZip(JDBCConnection.resultSet.getString(6).toString());
						
						if(JDBCConnection.resultSet.getObject(7) == null)
							pro.setAskingprice("");
						else
							pro.setAskingprice(JDBCConnection.resultSet.getString(7).toString());
						
						if(JDBCConnection.resultSet.getObject(8) == null)
							pro.setListdate("");
						else
							pro.setListdate(JDBCConnection.resultSet.getString(8).toString());
						if(JDBCConnection.resultSet.getObject(9) == null)
							pro.setSellerid("");
						else
							pro.setSellerid(JDBCConnection.resultSet.getString(9).toString());
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				property.setText(pro.getPropertyid());
				type.setText(pro.getType());
				street.setText(pro.getStreet());
				city.setText(pro.getCity());
				state.setText(pro.getState());
				zip.setText(pro.getZip());
				ask.setText(pro.getAskingprice());
				listdate.setText(pro.getListdate());
				seller.setSelectedItem(pro.getSellerid());
				
				type.setEditable(true);
				street.setEditable(true);
				city.setEditable(true);
				state.setEditable(true);
				zip.setEditable(true);
				ask.setEditable(true);
				listdate.setEditable(true);
				seller.setEditable(true);

			}
		});
		btnNewButton.setBounds(285, 38, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
	    
		
		
	}
}
