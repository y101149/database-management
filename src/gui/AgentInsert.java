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

import entity.SalesAgent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class AgentInsert {

	private JFrame frame;
	private JTextField agentid;
	private JTextField first;
	private JTextField last;
	private JTextField ext;
	private JTextField home;
	private JComboBox office = new JComboBox();


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					AgentInsert window = new AgentInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public AgentInsert() {
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
		
		JLabel lblAddAgent = new JLabel("Add Agent");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 20, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		JLabel lblAgentid = new JLabel("AgentID");
		lblAgentid.setBounds(61, 121, 83, 26);
		frame.getContentPane().add(lblAgentid);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(61, 180, 83, 26);
		frame.getContentPane().add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(61, 244, 83, 26);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblExt = new JLabel("EXT");
		lblExt.setBounds(61, 306, 83, 26);
		frame.getContentPane().add(lblExt);
		
		JLabel lblHomenumber = new JLabel("HomeNumber");
		lblHomenumber.setBounds(61, 368, 98, 26);
		frame.getContentPane().add(lblHomenumber);
		
		JLabel lblOfficeid = new JLabel("OfficeID");
		lblOfficeid.setBounds(61, 426, 83, 26);
		frame.getContentPane().add(lblOfficeid);
		
		agentid = new JTextField();
		agentid.setBounds(209, 120, 134, 28);
		frame.getContentPane().add(agentid);
		agentid.setColumns(10);
		
		first = new JTextField();
		first.setColumns(10);
		first.setBounds(209, 179, 134, 28);
		frame.getContentPane().add(first);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(209, 243, 134, 28);
		frame.getContentPane().add(last);
		
		ext = new JTextField();
		ext.setColumns(10);
		ext.setBounds(209, 305, 134, 28);
		frame.getContentPane().add(ext);
		
		home = new JTextField();
		home.setColumns(10);
		home.setBounds(209, 367, 134, 28);
		frame.getContentPane().add(home);
		
		String sqlstr = "select officeid from branch_offices";
		JDBCConnection.executeQuery(sqlstr);
	    office = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				office.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		office.setToolTipText("");
		office.setBounds(209, 427, 134, 27);
		frame.getContentPane().add(office);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				int flag1 = 0;
				SalesAgent sa = new SalesAgent();
				if(agentid.getText().isEmpty())
				{	
					flag = 1;
				
				}
				else
				{
					String sql = "select agentid from sales_agents";
					JDBCConnection.executeQuery(sql);
					while(JDBCConnection.next())
					{
						try {
							if(JDBCConnection.resultSet.getString(1).toString().equals(agentid.getText().toString()))
							{
								flag1 = 1;
								break;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					sa.setAgentid(agentid.getText());
					
				}
				
				if(first.getText().isEmpty())
					flag = 1;
				else
					sa.setFirstname(first.getText());
				
				if(last.getText().isEmpty())
					flag = 1;
				else
					sa.setLastname(last.getText());
				
				if(ext.getText().isEmpty())
					flag = 1;
				else
					sa.setExt(ext.getText());
				if(home.getText().isEmpty())
					sa.setHomenumber("null");
				else
					sa.setHomenumber(home.getText());
				sa.setOfficeid(office.getSelectedItem().toString());
				
				
				if(flag == 1)
					JOptionPane.showMessageDialog(null, "Some field must be NOT NULL", "ERROR", JOptionPane.ERROR_MESSAGE);
				if(flag1 == 1)
					JOptionPane.showMessageDialog(null, "Invalid Primary KEY", "Fail", JOptionPane.ERROR_MESSAGE);				
				if(flag == 0 && flag1 == 0)
				{
					String insertsql = "insert into sales_agents values ("
							+sa.getAgentid()+","+"'"+sa.getFirstname()+"'"+","+"'"+sa.getLastname()+"'"+","
							+"'"+sa.getExt()+"'"+","+"'"+sa.getHomenumber()+"'"+","+sa.getOfficeid()+",null,"+"null,"+"null,"+"null"+")";
					System.out.println(insertsql);
					JOptionPane.showMessageDialog(null, "Insert Success", "Success", JOptionPane.ERROR_MESSAGE);				

					//System.out.println(insertsql);
					JDBCConnection.executeUpdate(insertsql);
				}
			}
		});
		submit.setBounds(61, 486, 117, 29);
		frame.getContentPane().add(submit);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancel.setBounds(209, 486, 117, 29);
		frame.getContentPane().add(cancel);
		
		
	}
}
