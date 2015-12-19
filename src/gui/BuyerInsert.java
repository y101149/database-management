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

public class BuyerInsert {

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


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					BuyerInsert window = new BuyerInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public BuyerInsert() {
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
		
		JLabel lblAddAgent = new JLabel("Add Buyer");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 20, 340, 44);
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
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int flag1 = 0;
				Buyers bu = new Buyers();
				if(buyerid.getText().isEmpty())
					flag = 1;
				else
				{
					String sql = "select buyerid from buyers";
					JDBCConnection.executeQuery(sql);
					while(JDBCConnection.next())
					{
						try {
							if(JDBCConnection.resultSet.getString(1).toString().equals(buyerid.getText().toString()))
							{
								flag1 = 1;
								break;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					bu.setBuyerid(buyerid.getText());
				}
				
				if(first.getText().isEmpty())
					flag = 1;
				else
					bu.setFirstname(first.getText());
				
				if(last.getText().isEmpty())
					flag = 1;
				else
					bu.setLastname(last.getText());
				
				if(street.getText().isEmpty())
					bu.setStreet("null");
				else
					bu.setStreet(street.getText());
				
				if(city.getText().isEmpty())
					bu.setCity("null");
				else
					bu.setCity(city.getText());
				
				if(state.getText().isEmpty())
					bu.setState("null");
				else
					bu.setState(state.getText());
				
				if(zip.getText().isEmpty())
					bu.setZip("null");
				else
					bu.setZip(zip.getText());
				if(phone.getText().isEmpty())
					bu.setPhonenumber("null");
				else
					bu.setPhonenumber(phone.getText());
				bu.setAgentid(agent.getSelectedItem().toString());
				
				if(flag == 1)
					JOptionPane.showMessageDialog(null, "Some field must be NOT NULL", "ERROR", JOptionPane.ERROR_MESSAGE);	
				if(flag1 == 1)
					JOptionPane.showMessageDialog(null, "Invalid Primary KEY", "Fail", JOptionPane.ERROR_MESSAGE);				
				if(flag == 0 && flag1 == 0)
				{
					String insertsql = "insert into buyers values("
							+ bu.getBuyerid()+","+"'"+bu.getFirstname()+"'"+","
							+"'"+bu.getLastname()+"'"+"," +"'"+ bu.getStreet()
							+"'"+","+"'"+bu.getCity()+"'"+","+"'"+bu.getState()
							+"'"+","+"'"+bu.getZip()+"'"+ ","+"'"+bu.getPhonenumber()
							+"'"+","+bu.getAgentid()+")";
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
		buyerid.setColumns(10);
		buyerid.setBounds(209, 76, 134, 28);
		frame.getContentPane().add(buyerid);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(209, 370, 134, 28);
		frame.getContentPane().add(zip);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(209, 422, 134, 28);
		frame.getContentPane().add(phone);
		
		
	}
}
