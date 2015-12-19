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

public class SellerUpdate {

	private JFrame frame;
	private JTextField first;
	private JTextField last;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JComboBox agent = new JComboBox();
	private JComboBox contact = new JComboBox();
	private JComboBox pk = new JComboBox();

	private JTextField sellerid;
	private JTextField zip;
	private JTextField phone;


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					SellerUpdate window = new SellerUpdate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public SellerUpdate() {
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
		
		JLabel lblAddAgent = new JLabel("Update Seller");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(28, 0, 340, 44);
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
		String sql = "select * from sellers";
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
		pk.setBounds(138, 36, 134, 27);
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
		
		String sqlstr2 = "select id from contact_person";
		JDBCConnection.executeQuery(sqlstr2);
		contact = new JComboBox();
		contact.addItem("null");
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
				if(sellerid.getText().isEmpty())
					flag = 1;
				if(flag == 0)
				{
					Sellers se = new Sellers();
					se.setSellerid(sellerid.getText());
					se.setFirstname(first.getText());
					se.setLastname(last.getText());
					se.setStreet(street.getText());
					se.setCity(city.getText());
					se.setState(state.getText());
					se.setZip(zip.getText());
					se.setPhonenumber(phone.getText());
					se.setAgentid(agent.getSelectedItem().toString());
					se.setContactpersonid(contact.getSelectedItem().toString());
					String sql = "";
					//System.out.println(!se.getContactpersonid().isEmpty());
					if(!se.getContactpersonid().equals("null")){
					sql = "update sellers set firstname="
							+"'"+ se.getFirstname()+"',"+"lastname = "+ "'"+se.getLastname()+"'," + "street ="
							+"'"+ se.getStreet()+ "',"+"city = " + "'"+se.getCity()+"'," +"state ="
							+"'"+ se.getState()+ "',"+"zip ="+   "'"+se.getZip()+"'," + "phonenumber ="
							+"'" + se.getPhonenumber()+"',"+"sales_agents_agentid="+"'"+se.getAgentid()+"',"+"contact_person_id ="
							+"'"+ se.getContactpersonid()+"'"+" where sellerid = "+se.getSellerid() ;
					}
					
					else
					{
						sql = "update sellers set firstname="
								+"'"+ se.getFirstname()+"',"+"lastname = "+ "'"+se.getLastname()+"'," + "street ="
								+"'"+ se.getStreet()+ "',"+"city = " + "'"+se.getCity()+"'," +"state ="
								+"'"+ se.getState()+ "',"+"zip ="+   "'"+se.getZip()+"'," + "phonenumber ="
								+"'" + se.getPhonenumber()+"',"+"sales_agents_agentid="+"'"+se.getAgentid()+"',"+"contact_person_id="
								+"''"+" where sellerid = "+se.getSellerid() ;
					}
					System.out.println(sql);

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
		lblZip.setBounds(61, 371, 83, 26);
		frame.getContentPane().add(lblZip);
		
		JLabel lblPhonenumber = new JLabel("PhoneNumber");
		lblPhonenumber.setBounds(61, 423, 98, 26);
		frame.getContentPane().add(lblPhonenumber);
		
		JLabel lblAgentid_1 = new JLabel("AgentID");
		lblAgentid_1.setBounds(61, 471, 98, 26);
		frame.getContentPane().add(lblAgentid_1);
		
		sellerid = new JTextField();
		sellerid.setEditable(false);
		sellerid.setColumns(10);
		sellerid.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(sellerid);
		
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
		
		JLabel lblContactid = new JLabel("ContactID");
		lblContactid.setBounds(61, 516, 98, 26);
		frame.getContentPane().add(lblContactid);
		
		JLabel lblInputPk = new JLabel("Input PK");
		lblInputPk.setBounds(61, 35, 83, 26);
		frame.getContentPane().add(lblInputPk);
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String primary = "";
				primary = pk.getSelectedItem().toString();
				Sellers se = new Sellers();
				String sql1 = "select * from sellers where sellerid ="+primary; 
				JDBCConnection.executeQuery(sql1);
				while(JDBCConnection.next())
				{
					try {

						se.setSellerid(JDBCConnection.resultSet.getString(1).toString());
						se.setFirstname(JDBCConnection.resultSet.getString(2).toString());
						se.setLastname(JDBCConnection.resultSet.getString(3).toString());
						
						if(JDBCConnection.resultSet.getObject(4) == null)
							se.setStreet("");
						else
							se.setStreet(JDBCConnection.resultSet.getString(4).toString());
						if(JDBCConnection.resultSet.getObject(5) == null)
							se.setCity("");
						else
							se.setStreet(JDBCConnection.resultSet.getString(4).toString());
						if(JDBCConnection.resultSet.getObject(6) == null)
							se.setState("");
						else
							se.setState(JDBCConnection.resultSet.getString(6).toString());
						if(JDBCConnection.resultSet.getObject(7) == null)
							se.setZip("");
						else
							se.setZip(JDBCConnection.resultSet.getString(7).toString());
						if(JDBCConnection.resultSet.getObject(8) == null)
							se.setPhonenumber("");
						else
							se.setPhonenumber(JDBCConnection.resultSet.getString(8).toString());
						if(JDBCConnection.resultSet.getObject(9) == null)
							se.setAgentid("");
						else
							se.setAgentid(JDBCConnection.resultSet.getString(9).toString());
						
						
						if(JDBCConnection.resultSet.getObject(10) == null)
							se.setContactpersonid("");
						else
							se.setContactpersonid(JDBCConnection.resultSet.getString(10).toString());
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				sellerid.setText(se.getSellerid());
				first.setText(se.getFirstname());
				last.setText(se.getLastname());
				street.setText(se.getStreet());
				city.setText(se.getCity());
				state.setText(se.getState());
				zip.setText(se.getZip());
				phone.setText(se.getPhonenumber());
				agent.setSelectedItem(se.getAgentid());
				contact.setSelectedItem(se.getContactpersonid());
				
				first.setEditable(true);
				last.setEditable(true);
				street.setEditable(true);
				city.setEditable(true);
				state.setEditable(true);
				zip.setEditable(true);
				phone.setEditable(true);
				

				
				
			}
		});
		btnSearch.setBounds(285, 35, 117, 29);
		frame.getContentPane().add(btnSearch);
		
	    
		
		
	}
}
