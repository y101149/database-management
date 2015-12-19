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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class BuyerDelete {

	private JFrame frame;
	private JTextField first;
	private JTextField last;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JComboBox agent = new JComboBox();
	private JTextField buyerid;
	private JTextField zip;
	private JTextField phone;
	private	JComboBox pk = new JComboBox();



	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					BuyerDelete window = new BuyerDelete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public BuyerDelete() {
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
		
		JLabel lblAddAgent = new JLabel("Delete Buyer");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(25, 0, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("BuyerID");
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
		first.setEditable(false);
		first.setBounds(209, 120, 134, 28);
		frame.getContentPane().add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setEditable(false);
		last.setColumns(10);
		last.setBounds(209, 168, 134, 28);
		frame.getContentPane().add(last);
		
		street = new JTextField();
		street.setEditable(false);
		street.setColumns(10);
		street.setBounds(209, 217, 134, 28);
		frame.getContentPane().add(street);
		
		city = new JTextField();
		city.setEditable(false);
		city.setColumns(10);
		city.setBounds(209, 268, 134, 28);
		frame.getContentPane().add(city);
		
		state = new JTextField();
		state.setEditable(false);
		state.setColumns(10);
		state.setBounds(209, 313, 134, 28);
		frame.getContentPane().add(state);
		
		
		pk = new JComboBox();
		pk.setToolTipText("");
		pk.setBounds(123, 37, 134, 27);
		String sql = "select * from buyers";
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
		
		JButton submit = new JButton("Delete");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				if(buyerid.getText().isEmpty())
					flag = 1;
				if(flag == 0){
				Object[] options ={ "YES", "NO" };  
				int m = JOptionPane.showOptionDialog(null, "Are you sure to delete the record", "Delete?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
				Buyers by = new Buyers();
				by.setBuyerid(buyerid.getText());
				by.setFirstname(first.getText());
				by.setLastname(last.getText());
				by.setStreet(street.getText());
				by.setCity(city.getText());
				by.setState(state.getText());
				by.setZip(zip.getText());
				by.setPhonenumber(phone.getText());
				by.setAgentid(agent.getSelectedItem().toString());

				String sql = "delete from buyers where buyerid =" + by.getBuyerid();
//				String sql = "update buyers set firstname="
//						+"'"+ by.getFirstname()+"',"+"lastname = "+ "'"+by.getLastname()+"'," + "street ="
//						+"'"+ by.getStreet()      + "',"+"city = " + "'"+by.getCity()+"'," + "state ="
//						+"'"+ by.getState() + "',"+"zip = " + "'"+by.getZip()+"',"+"phonenumber = " + "'"+by.getPhonenumber()+"',"
//						+"sales_agents_agentid =" +"'"+by.getAgentid()+"'" +"where buyerid = "+by.getBuyerid() ;
				if(m == 0){
					JDBCConnection.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Delete Success", "Success", JOptionPane.ERROR_MESSAGE);				
					}
				else
					JOptionPane.showMessageDialog(null, "Delete Cancel", "Cancel", JOptionPane.ERROR_MESSAGE);	

				}
				else
					JOptionPane.showMessageDialog(null, "PLS SEARCH FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);				

				//System.out.println(sql);
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
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(61, 371, 83, 26);
		frame.getContentPane().add(lblZip);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(61, 423, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblAgentid_1 = new JLabel("AgentID");
		lblAgentid_1.setBounds(61, 471, 98, 26);
		frame.getContentPane().add(lblAgentid_1);
		
		buyerid = new JTextField();
		buyerid.setEditable(false);
		buyerid.setColumns(10);
		buyerid.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(buyerid);
		
		zip = new JTextField();
		zip.setEditable(false);
		zip.setColumns(10);
		zip.setBounds(209, 370, 134, 28);
		frame.getContentPane().add(zip);
		
		phone = new JTextField();
		phone.setEditable(false);
		phone.setColumns(10);
		phone.setBounds(209, 422, 134, 28);
		frame.getContentPane().add(phone);
		
		JLabel lblInput = new JLabel("Input PK");
		lblInput.setBounds(61, 42, 61, 16);
		frame.getContentPane().add(lblInput);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String primary = "";
				primary = pk.getSelectedItem().toString();
				Buyers by = new Buyers();
				String sql = "select * from buyers where buyerid ="+primary;
				JDBCConnection.executeQuery(sql);
				while(JDBCConnection.next())
				{
					try {
						if(JDBCConnection.resultSet.getString(1).toString().equals(primary))
						{
							by.setBuyerid(JDBCConnection.resultSet.getString(1).toString());
							by.setFirstname(JDBCConnection.resultSet.getString(2).toString());
							by.setLastname(JDBCConnection.resultSet.getString(3).toString());
							if(JDBCConnection.resultSet.getObject(4) == null)
								by.setStreet("");
							else
								by.setStreet(JDBCConnection.resultSet.getString(4).toString());
							
							if(JDBCConnection.resultSet.getObject(5) == null)
								by.setCity("");
							else
								by.setCity(JDBCConnection.resultSet.getString(5).toString());
							
							if(JDBCConnection.resultSet.getObject(6) == null)
								by.setState("");
							else
								by.setState(JDBCConnection.resultSet.getString(6).toString());
							
							if(JDBCConnection.resultSet.getObject(7) == null)
								by.setZip("");
							else
								by.setZip(JDBCConnection.resultSet.getString(7).toString());
							
							if(JDBCConnection.resultSet.getObject(8) == null)
								by.setPhonenumber("");
							else
								by.setPhonenumber(JDBCConnection.resultSet.getString(8).toString());
							
							if(JDBCConnection.resultSet.getObject(9) == null)
								by.setAgentid("");
							else
								by.setAgentid(JDBCConnection.resultSet.getString(9).toString());
							
							
							
							
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
					buyerid.setText(by.getBuyerid());
					first.setText(by.getFirstname());
					last.setText(by.getLastname());
					street.setText(by.getStreet());
					city.setText(by.getCity());
					state.setText(by.getState());
					zip.setText(by.getZip());
					phone.setText(by.getPhonenumber());
					agent.setSelectedItem(by.getAgentid());
				
					

			
			}
		});
		btnNewButton.setBounds(285, 37, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
