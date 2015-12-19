package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.Panel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.SalesAgent;
import entity.Sellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JDBCConnection;

import java.awt.Toolkit;

public class MainFrame {

	private JFrame frame;
	private JTable table;
	
	public JPanel centerpanel = new JPanel();
	public JScrollPane scrollPane = new JScrollPane();
	public String tablesselected = "";


	/**
	 * Launch the application.
	 */
	
	public void run() {
		try {
				MainFrame window = new MainFrame();
				window.frame.setVisible(true);
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
	

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1058, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel leftpanel = new JPanel();
		leftpanel.setBounds(0, 0, 119, 594);
		frame.getContentPane().add(leftpanel);
		leftpanel.setLayout(null);
	    //JPanel centerpanel = new JPanel();
		centerpanel.setBounds(125, 45, 927, 549);
		frame.getContentPane().add(centerpanel);
		centerpanel.setLayout(null);
		
		final JButton agentinfo = new JButton("Agent Info");
		agentinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "agent";
				String[] columnNames = new String[10];
				String count;
				int row = 0;
				columnNames[0] = "AgentID";
				columnNames[1] = "Firstname";
				columnNames[2] = "Lastname";
				columnNames[3] = "EXT";
				columnNames[4] = "Homenumber";
				columnNames[5] = "OfficeID";
				columnNames[6] = "CreatedDate";
				columnNames[7] = "CreatedBy";
				columnNames[8] = "UpdatedDate";
				columnNames[9] = "UpdatedBy";
				String sqlcount = "select count(*) from sales_agents";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][10];

				String sqlstr = "select * from sales_agents";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 10; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
		});
		//JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 915, 534);
		centerpanel.add(scrollPane);
		agentinfo.setBounds(0, 117, 117, 29);
		leftpanel.add(agentinfo);
		
		final JButton buyerinfo = new JButton("Buyer Info");
		buyerinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "buyer";

				String[] columnNames = new String[9];
				String count;
				int row = 0;
				columnNames[0] = "BuyerID";
				columnNames[1] = "Firstname";
				columnNames[2] = "Lastname";
				columnNames[3] = "Street";
				columnNames[4] = "City";
				columnNames[5] = "Street";
				columnNames[6] = "Zip";
				columnNames[7] = "PhoneNumber";
				columnNames[8] = "AgentID";
				String sqlcount = "select count(*) from buyers";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][9];

				String sqlstr = "select * from buyers";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 9; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
			
		});
		buyerinfo.setBounds(0, 191, 117, 29);
		leftpanel.add(buyerinfo);
		
		final JButton sellerinfo = new JButton("Seller Info");
		sellerinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "seller";

				String[] columnNames = new String[11];
				String count;
				int row = 0;
				columnNames[0] = "SellerID";
				columnNames[1] = "Firstname";
				columnNames[2] = "Lastname";
				columnNames[3] = "Street";
				columnNames[4] = "City";
				columnNames[5] = "State";
				columnNames[6] = "Zip";
				columnNames[7] = "PhoneNumber";
				columnNames[8] = "AgentID";
				columnNames[9] = "ContactPersonID";
				columnNames[10] = "Createddate";
				String sqlcount = "select count(*) from sellers";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][11];

				String sqlstr = "select * from sellers";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 11; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
			
		});
		sellerinfo.setBounds(0, 252, 117, 29);
		leftpanel.add(sellerinfo);
		
		final JButton officeinfo = new JButton("Office Info");
		officeinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "office";

				String[] columnNames = new String[10];
				String count;
				int row = 0;
				columnNames[0] = "OfficdID";
				columnNames[1] = "OfficeName";
				columnNames[2] = "Address";
				columnNames[3] = "MailBOX";
				columnNames[4] = "PhoneNumber";
				columnNames[5] = "ManagerID";
				columnNames[6] = "CreatedDate";
				columnNames[7] = "CreatedBy";
				columnNames[8] = "UpdatedDate";
				columnNames[9] = "UpdatedBy";
				String sqlcount = "select count(*) from branch_offices";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][10];

				String sqlstr = "select * from branch_offices";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 10; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
			
		});
		officeinfo.setBounds(0, 314, 117, 29);
		leftpanel.add(officeinfo);
		
		final JButton propertyinfo = new JButton("Property");
		propertyinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "property";

				String[] columnNames = new String[12];
				String count;
				int row = 0;
				columnNames[0] = "PropertyID";
				columnNames[1] = "Type";
				columnNames[2] = "Street";
				columnNames[3] = "City";
				columnNames[4] = "State";
				columnNames[5] = "Zip";
				columnNames[6] = "AskingPrice";
				columnNames[7] = "ListDate";
				columnNames[8] = "SellerID";
				columnNames[9] = "CreatedDate";
				columnNames[10] = "CreatedBy";
				columnNames[11] = "UpdatedDate";


				String sqlcount = "select count(*) from properties";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][12];

				String sqlstr = "select * from properties";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 12; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
			
		});
		propertyinfo.setBounds(0, 381, 117, 29);
		leftpanel.add(propertyinfo);
		
		final JButton contact = new JButton("Contact Info");
		contact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "contact";

				String[] columnNames = new String[5];
				String count;
				int row = 0;
				columnNames[0] = "ContactID";
				columnNames[1] = "Tittle";
				columnNames[2] = "Firstname";
				columnNames[3] = "Lastname";
				columnNames[4] = "PhoneNumber";
				


				String sqlcount = "select count(*) from contact_person";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][5];

				String sqlstr = "select * from contact_person";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 5; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
			
				
		});
		contact.setBounds(0, 451, 117, 29);
		leftpanel.add(contact);
		
		final JButton interests = new JButton("Interests");
		interests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablesselected = "interest";

				String[] columnNames = new String[7];
				String count;
				int row = 0;
				columnNames[0] = "BuyersID";
				columnNames[1] = "BuyersFirstname";
				columnNames[2] = "BuyersLastname";
				columnNames[3] = "SellersID";
				columnNames[4] = "SellersFirstname";
				columnNames[5] = "SellersLastname";
				columnNames[6] = "PropertyID";
				


				String sqlcount = "select count(*) from buyer_interests";
				JDBCConnection.executeQuery(sqlcount);
				while (JDBCConnection.next())
				{
					try {
						count = JDBCConnection.resultSet.getString(1);
						row = Integer.parseInt(count);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				String[][] data = new String[row][7];

				String sqlstr = "select buyer_interests.buyers_buyerid,buyers.firstname,buyers.lastname"
						+ ",sellers.sellerid, sellers.firstname, sellers.lastname,buyer_interests.properties_propertyid from buyer_interests,buyers,properties,sellers where "
						+ "buyer_interests.buyers_buyerid = buyers.buyerid and buyer_interests.properties_propertyid = properties.propertyid"
						+ " and properties.sellers_sellerid = sellers.sellerid";
				JDBCConnection.executeQuery(sqlstr);

				int i = 0;
				while(JDBCConnection.next())
				{
					for(int column = 0; column < 7; column++)
					{
						try {
							data[i][column] = JDBCConnection.resultSet.getString(column + 1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//						System.out.println(data[i][column]);
					}
					i++;
				}
				
			    
			    table = new JTable(data,columnNames);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
			
			
		});
		interests.setBounds(0, 518, 117, 29);
		leftpanel.add(interests);
		
		JPanel toppanel = new JPanel();
		toppanel.setBounds(125, 0, 927, 43);
		frame.getContentPane().add(toppanel);
		toppanel.setLayout(null);
		
		JButton insert = new JButton("Insert");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablesselected.equals("agent"))
				{
					AgentInsert ag = new AgentInsert();
					ag.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("buyer"))
				{
					BuyerInsert bu = new BuyerInsert();
					bu.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("seller"))
				{
					SellerInsert se = new SellerInsert();
					se.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("office"))
				{
					OfficeInsert of = new OfficeInsert();
					of.run();
				}
	
				else if(tablesselected.equalsIgnoreCase("property"))
				{
					PropertyInsert pr = new PropertyInsert();
					pr.run();
				}
				else if(tablesselected.equalsIgnoreCase("contact"))
				{
					ContactInsert ci = new ContactInsert();
					ci.run();
				}
				else if(tablesselected.equalsIgnoreCase("interest"))
				{
					InterestInsert ii = new InterestInsert();
					ii.run();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select table first", "Need Selected Table", JOptionPane.ERROR_MESSAGE);				

				}
			}
		});
		insert.setBounds(6, 8, 117, 29);
		toppanel.add(insert);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(tablesselected.equals("agent"))
				{
					AgentUpdate au = new AgentUpdate();
					au.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("buyer"))
				{
					BuyerUpdate bu = new BuyerUpdate();
					bu.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("seller"))
				{
					SellerUpdate su = new SellerUpdate();
					su.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("office"))
				{
					OfficeUpdate ou = new OfficeUpdate();
					ou.run();
				}
	
				else if(tablesselected.equalsIgnoreCase("property"))
				{
					PropertyUpdate pu = new PropertyUpdate();
					pu.run();
				}
				else if(tablesselected.equalsIgnoreCase("contact"))
				{
					ContactUpdate ci = new ContactUpdate();
					ci.run();
				}
				else if(tablesselected.equalsIgnoreCase("interest"))
				{
					InterestUpdate iu = new InterestUpdate();
					iu.run();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select table first", "Need Selected Table", JOptionPane.ERROR_MESSAGE);				

				}
			
			}
		});
		update.setBounds(113, 8, 117, 29);
		toppanel.add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if(tablesselected.equals("agent"))
				{
					AgentDelete ad = new AgentDelete();
					ad.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("buyer"))
				{
					BuyerDelete bu = new BuyerDelete();
					bu.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("seller"))
				{
					SellerDelete su = new SellerDelete();
					su.run();
					
				}
				else if(tablesselected.equalsIgnoreCase("office"))
				{
					OfficeDelete ou = new OfficeDelete();
					ou.run();
				}
	
				else if(tablesselected.equalsIgnoreCase("property"))
				{
					PropertyDelete pu = new PropertyDelete();
					pu.run();
				}
				else if(tablesselected.equalsIgnoreCase("contact"))
				{
					ContactDelete ci = new ContactDelete();
					ci.run();
				}
				else if(tablesselected.equalsIgnoreCase("interest"))
				{
					InterestDelete iu = new InterestDelete();
					iu.run();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select table first", "Need Selected Table", JOptionPane.ERROR_MESSAGE);				

				}
			
			
			}
		});
		delete.setBounds(222, 8, 117, 29);
		toppanel.add(delete);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				if(tablesselected.equals("agent"))
				{
					
					agentinfo.doClick();
				}
				else if(tablesselected.equalsIgnoreCase("buyer"))
				{
					buyerinfo.doClick();
					
				}
				else if(tablesselected.equalsIgnoreCase("seller"))
				{
					sellerinfo.doClick();
					
				}
				else if(tablesselected.equalsIgnoreCase("office"))
				{
					officeinfo.doClick();
				}
	
				else if(tablesselected.equalsIgnoreCase("property"))
				{
					propertyinfo.doClick();
				}
				else if(tablesselected.equalsIgnoreCase("contact"))
				{
					contact.doClick();
				}
				else if(tablesselected.equalsIgnoreCase("interest"))
				{
					interests.doClick();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select table first", "Need Selected Table", JOptionPane.ERROR_MESSAGE);				

				}
			
			
			
			}
		});
		btnRefresh.setBounds(331, 8, 117, 29);
		toppanel.add(btnRefresh);
		
		
		
		
	}
}
