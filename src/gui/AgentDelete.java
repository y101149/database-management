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

public class AgentDelete {

	private JFrame frame;
	private JTextField agentid;
	private JTextField first;
	private JTextField last;
	private JTextField ext;
	private JTextField home;
	private JComboBox office = new JComboBox();
	private JComboBox pk = new JComboBox();



	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					AgentDelete window = new AgentDelete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public AgentDelete() {
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
		
		JLabel lblAddAgent = new JLabel("Delete Agent");
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
		
		JLabel search = new JLabel("Lastname");
		search.setBounds(61, 244, 83, 26);
		frame.getContentPane().add(search);
		
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
		agentid.setEditable(false);
		agentid.setBounds(209, 120, 134, 28);
		frame.getContentPane().add(agentid);
		agentid.setColumns(10);
		
		first = new JTextField();
		first.setEditable(false);
		first.setColumns(10);
		first.setBounds(209, 179, 134, 28);
		frame.getContentPane().add(first);
		
		last = new JTextField();
		last.setEditable(false);
		last.setColumns(10);
		last.setBounds(209, 243, 134, 28);
		frame.getContentPane().add(last);
		
		ext = new JTextField();
		ext.setEditable(false);
		ext.setColumns(10);
		ext.setBounds(209, 305, 134, 28);
		frame.getContentPane().add(ext);
		
		home = new JTextField();
		home.setEditable(false);
		home.setColumns(10);
		home.setBounds(209, 367, 134, 28);
		frame.getContentPane().add(home);
		
		
		String sql2 = "select * from sales_agents";
		
		pk = new JComboBox();
		pk.setToolTipText("");
		pk.setBounds(149, 72, 134, 27);
		JDBCConnection.executeQuery(sql2);
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
		
		JButton submit = new JButton("Delete");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				if(agentid.getText().isEmpty())
					flag = 1;
				if(flag == 0)
				{
					Object[] options ={ "YES", "NO" };  
					int m = JOptionPane.showOptionDialog(null, "Are you sure to delete the record", "Delete?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
					SalesAgent sa = new SalesAgent();
				
					sa.setAgentid(agentid.getText());
					sa.setFirstname(first.getText());
					sa.setLastname(last.getText());
					sa.setExt(ext.getText());
					sa.setHomenumber(home.getText());
					sa.setOfficeid(office.getSelectedItem().toString());
				
					String sql = "delete from sales_agents where agentid =" + sa.getAgentid();
					System.out.println(sql);
//					String sql = "update sales_agents set firstname="
//						+"'"+ sa.getFirstname()+"',"+"lastname = "+ "'"+sa.getLastname()+"'," + "ext_ ="
//						+"'"+ sa.getExt()      + "',"+"homenumber = " + "'"+sa.getHomenumber()+"'," + "branch_offices_officeid ="
//								+"'"+ sa.getOfficeid() + "' "+"where agentid = "+sa.getAgentid() ;
					if(m == 0)
					{
						JDBCConnection.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Delete Success", "Success", JOptionPane.ERROR_MESSAGE);	
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Delete Cancel", "Cancel", JOptionPane.ERROR_MESSAGE);	

					}
				}
				else
					JOptionPane.showMessageDialog(null, "PLS SEARCH FIRST", "ERROR", JOptionPane.ERROR_MESSAGE);				

					
				//System.out.println(sql);

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
		
		JLabel lblPlsInputThe = new JLabel("PLS INPUT THE PK ");
		lblPlsInputThe.setBounds(16, 76, 134, 16);
		frame.getContentPane().add(lblPlsInputThe);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String primary = "";
				primary = pk.getSelectedItem().toString();
				SalesAgent sa = new SalesAgent();
				String sql = "select * from sales_agents where agentid=" + primary ;
				JDBCConnection.executeQuery(sql);
				while(JDBCConnection.next())
				{
					try {
						if(JDBCConnection.resultSet.getString(1).toString().equals(primary))
						{
							sa.setAgentid(JDBCConnection.resultSet.getString(1).toString());
							sa.setFirstname(JDBCConnection.resultSet.getString(2).toString());
							sa.setLastname(JDBCConnection.resultSet.getString(3).toString());
							sa.setExt(JDBCConnection.resultSet.getString(4).toString());
							if(JDBCConnection.resultSet.getObject(5) == null)
								sa.setHomenumber("");
							else
								sa.setHomenumber(JDBCConnection.resultSet.getString(5).toString());
							//sa.setHomenumber(JDBCConnection.resultSet.getString(5).toString());
							sa.setOfficeid(JDBCConnection.resultSet.getString(6).toString());
							
							
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
					agentid.setText(sa.getAgentid());
					first.setText(sa.getFirstname());
					last.setText(sa.getLastname());
					ext.setText(sa.getExt());
					home.setText(sa.getHomenumber());
					office.setSelectedItem(sa.getOfficeid());
					
					
					
			}
		});
		btnSearch.setBounds(295, 71, 117, 29);
		frame.getContentPane().add(btnSearch);
		
		
		
	}
}
